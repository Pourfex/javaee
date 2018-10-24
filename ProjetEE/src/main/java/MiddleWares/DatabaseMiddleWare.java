package MiddleWares;

import Model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseMiddleWare {

    EntityManagerFactory emf;
    EntityManager em;

    public DatabaseMiddleWare(){
        emf = Persistence.createEntityManagerFactory("capteurdb");
        em = emf.createEntityManager();
    }

    private void beginTransaction() {
        em.getTransaction().begin();
    }

    private void endTransaction() {
        em.getTransaction().commit();
        //em.close();
    }

    public HashMap<Capteur, List<CapteurdataEntity>> getCapteurById(int search_id){
        HashMap<Capteur, List<CapteurdataEntity>> result = new HashMap<>();
        List<CapteurdataEntity> capteurdatas = new ArrayList<>();
        Capteur capteur;

        beginTransaction();

        List<CapteurEntity> capteurs =  em.createQuery("SELECT p FROM CapteurEntity p where p.id =" + String.valueOf(search_id)).getResultList();
        if(capteurs.size() != 1){
            endTransaction();
            return result;
        }else{
            CapteurEntity capteurEntity = capteurs.get(0);
            endTransaction();

            beginTransaction();
            List<VilleEntity> villes = em.createQuery("SELECT p FROM VilleEntity p where p.id =" + capteurEntity.getIdVille()).getResultList();
            if(villes.size() != 1){
                endTransaction();
                return result;
            }

            VilleEntity villeEntity = villes.get(0);

            endTransaction();

            beginTransaction();
            List<PaysEntity> paysEntities = em.createQuery("SELECT p FROM PaysEntity p where p.id =" + villeEntity.getIdPays()).getResultList();
            if(paysEntities.size() != 1){
                endTransaction();
                return result;
            }
            PaysEntity paysEntity = paysEntities.get(0);

            endTransaction();

            capteur = new Capteur(capteurEntity,paysEntity,villeEntity);
        }

        beginTransaction();
        capteurdatas = em.createQuery("SELECT p FROM CapteurdataEntity p where p.idCapteur =" + String.valueOf(search_id)).getResultList();

        result.put(capteur,capteurdatas);

        endTransaction();
        return result;
    }

    public HashMap<Capteur, List<CapteurdataEntity>> getCapteurByIds(List<Integer> ids){


        HashMap<Capteur, List<CapteurdataEntity>> result= new HashMap<>();
        for(Integer id : ids){
            HashMap<Capteur, List<CapteurdataEntity>> tmp= getCapteurById(id);
            result.putAll(tmp);
        }
        return result;
    }

    public HashMap<Capteur, List<CapteurdataEntity>> getAll(){

        HashMap<Capteur, List<CapteurdataEntity>> result;
        result = new HashMap<>();

        beginTransaction();
        List<CapteurEntity> capteurs = em.createQuery("SELECT p FROM CapteurEntity p").getResultList();
        endTransaction();

        beginTransaction();
        List<CapteurdataEntity> capteursDatas = em.createQuery("SELECT p FROM CapteurdataEntity p").getResultList();
        endTransaction();

        beginTransaction();
        List<VilleEntity> villes = em.createQuery("SELECT p FROM VilleEntity p").getResultList();
        endTransaction();

        beginTransaction();
        List<PaysEntity> pays = em.createQuery("SELECT p FROM PaysEntity p").getResultList();
        endTransaction();

        for(CapteurEntity capteurEntity : capteurs){
            List<CapteurdataEntity> capteurdatas = new ArrayList<>();
            VilleEntity ville = null;
            PaysEntity pay_s = null;
            for(CapteurdataEntity capteursData : capteursDatas){
                if(capteursData.getIdCapteur() == capteurEntity.getId()){
                    capteurdatas.add(capteursData);
                }
            }
            for(VilleEntity villeEntity : villes){
                if(villeEntity.getId() == capteurEntity.getIdVille()){
                    ville = villeEntity;
                }
                for(PaysEntity paysEntity : pays){
                    if(paysEntity.getId() == villeEntity.getIdPays()){
                        pay_s = paysEntity;
                    }
                }
            }
            Capteur capteur = new Capteur(capteurEntity,pay_s,ville);
            result.put(capteur, capteurdatas);
        }
        return result;
    }

}
