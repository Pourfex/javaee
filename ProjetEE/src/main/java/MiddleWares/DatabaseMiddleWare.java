package MiddleWares;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

    public HashMap<CapteurDataType, Integer> getSeuilCapteurs(){
        HashMap<CapteurDataType, Integer> result = new HashMap<>();

        beginTransaction();
        List<SeuilCapteursEntity>  seuilCapteursEntities= em.createQuery("SELECT p FROM SeuilCapteursEntity p").getResultList();

        for(SeuilCapteursEntity seuilCapteursEntity : seuilCapteursEntities){
            result.put(parseStringForCapteurDataType(seuilCapteursEntity.getType()), seuilCapteursEntity.getValue());
        }
        endTransaction();
        return result;
    }

    private CapteurDataType parseStringForCapteurDataType(String type) {
        switch (type){
            case "HUMIDITE" : return CapteurDataType.HUMIDITE;
            case "TEMPERATURE" : return CapteurDataType.TEMPERATURE;
            case "VITESSE_VENT" : return CapteurDataType.VITESSE_VENT;
            case "PRESSION" : return CapteurDataType.PRESSION;
            default: return CapteurDataType.HUMIDITE;
        }
    }

    public void setSeuilCapteurs (HashMap<CapteurDataType, Integer> newSeuilCapteurs){
        for(CapteurDataType capteurDataType : newSeuilCapteurs.keySet()){
            beginTransaction();
            Query query = em.createQuery("UPDATE SeuilCapteursEntity set value = :newvalue " + "where type = :typee");
            query.setParameter("newvalue", newSeuilCapteurs.get(capteurDataType) );
            query.setParameter("typee", capteurDataType.toString() );
            endTransaction();
        }
    }

    public  List<CapteurdataEntity> getCapteurDataByCapteurId(int capteur_id){
        List<CapteurdataEntity> capteurdatas = new ArrayList<>();

        beginTransaction();
        capteurdatas = em.createQuery("SELECT p FROM CapteurdataEntity p where p.idCapteur =" + String.valueOf(capteur_id)).getResultList();

       endTransaction();
       return capteurdatas;
    }

    public Capteur2 getCapteur2byId(int search_id){

        Capteur2 result = null;

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

            result = new Capteur2(capteurEntity,paysEntity,villeEntity);
        }

        return result;
    }

    public List<Capteur2> getCapteur2byIds(List<Integer> ids){
        List<Capteur2> result= new ArrayList<>();
        for(Integer id : ids){
            result.add(getCapteur2byId(id));
        }
        return result;
    }

    public List<Capteur2> getAllCapteur2(){

        List<Capteur2> result= new ArrayList<>();


        beginTransaction();
        List<CapteurEntity> capteurs = em.createQuery("SELECT p FROM CapteurEntity p").getResultList();
        endTransaction();

        for(CapteurEntity capteurEntity :capteurs){
            result.add(getCapteur2byId(capteurEntity.getId()));
        }

        return result;
    }

    public boolean userInDb(String username, String password) {

        beginTransaction();
        List<IdentifiantEntity> identifiantsEntities = em.createQuery("SELECT p FROM IdentifiantEntity p").getResultList();
        endTransaction();

        for(IdentifiantEntity identifiantsEntity : identifiantsEntities){
            if(identifiantsEntity.getUser().equals(username) && identifiantsEntity.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    
    public List<CapteurdataEntity> getCapteurDataFromDate(int id, Timestamp time) {

        beginTransaction();
//        List<CapteurdataEntity> returnValue = em.createQuery("SELECT e FROM CapteurdataEntity e WHERE e.idCapteur = " + id + " AND e.timestamp.fastTime >= " + time).getResultList();
        Query q = em.createQuery("SELECT e FROM CapteurdataEntity e WHERE e.idCapteur = :id AND e.timestamp >= :time");
        q.setParameter("time", time);
        q.setParameter("id", id);
        List<CapteurdataEntity> returnValue = q.getResultList();
        endTransaction();
        
        Collections.sort(returnValue, new Comparator<CapteurdataEntity>() {
			public int compare(CapteurdataEntity arg0, CapteurdataEntity arg1) {
				return arg0.getTimestamp().before(arg1.getTimestamp()) ? -1 : 1;
			}});
        return returnValue;
    }

    public List<Capteur2> getAllCapteurByResearchBar(ArrayList<String> ids_result, ArrayList<String> tags_result, ArrayList<String> types_result) {
        List<Capteur2> result= new ArrayList<>();

        /*To test :     */

        //test filterByTypes :
       /* types_result = new ArrayList<>();
        types_result.add("temperature");
        types_result.add("pression");*/


        //test filterByTags :
        /*tags_result = new ArrayList<>();
        tags_result.add("raffinerie");
        tags_result.add("reacteurs");*/


        //test filterByIds :
        /*ids_result = new ArrayList<>();
        ids_result.add("1");
        ids_result.add("2");*/



        boolean allIds = ids_result.get(0).equals("all");
        boolean allTags = tags_result.get(0).equals("all");
        boolean allTypes = types_result.get(0).equals("all");

        if(allIds){
            if(allTags){
                if(allTypes){
                    result = getAllCapteur2();
                }else{
                    result = filterByTypes(types_result);
                }
            }else{
                if(allTypes){
                    result = filterByTags(tags_result);
                }else{
                    result = filterByTypesAndTags(types_result, tags_result);
                }
            }
        }else{
            if(allTags){
                if(allTypes){
                    result = filterByIds(ids_result);
                }else{
                    result = filterByTypesAndIds(types_result,ids_result);
                }
            }else{
                if(allTypes){
                    result = filterByTagsAndIds(tags_result,ids_result);
                }else{
                    result = filterByTypesAndTagsAndIds(types_result, tags_result,ids_result);
                }
            }
        }
        return result;
    }

    private List<Capteur2> filterByTagsAndIds(ArrayList<String> tags_result, ArrayList<String> ids_result) {
        List<Capteur2> result= new ArrayList<>();
        result = filterByIds(ids_result);
        filterListByTags(tags_result, result);
        return result;
    }

    private List<Capteur2> filterByTypesAndTagsAndIds(ArrayList<String> types_result, ArrayList<String> tags_result, ArrayList<String> ids_result) {
        List<Capteur2> result= new ArrayList<>();
        result = filterByIds(ids_result);

        filterListByTags(tags_result, result);
        filterListByTypes(types_result,result);

        return result;
    }

    private List<Capteur2> filterByTypesAndIds(ArrayList<String> types_result, ArrayList<String> ids_result) {
        List<Capteur2> result= new ArrayList<>();
        result = filterByIds(ids_result);

        filterListByTypes(types_result,result);

        return result;
    }

    private List<Capteur2> filterByIds(ArrayList<String> ids_result) {
        ArrayList<Integer> mapper = new ArrayList<>();
        for(String s : ids_result) {
            mapper.add(Integer.valueOf(s));
        }
        return getCapteur2byIds(mapper);
    }

    private List<Capteur2> filterByTypesAndTags(ArrayList<String> types_result, ArrayList<String> tags_result) {
        List<Capteur2> result= new ArrayList<>();
        result = getAllCapteur2();

        filterListByTags(tags_result, result);
        filterListByTypes(types_result,result);

        return result;

    }

    private List<Capteur2> filterByTags(ArrayList<String> tags_result) {
        List<Capteur2> result= new ArrayList<>();
        result = getAllCapteur2();

        filterListByTags(tags_result, result);
        return result;
    }

    private void filterListByTags(ArrayList<String> tags_result, List<Capteur2> result) {
        Iterator<Capteur2> i = result.iterator();
        while (i.hasNext()) {
            Capteur2 capteur2 = i.next();
            CapteurTags capteurTag = capteur2.getTag();
            boolean keep= false;
            for(String s : tags_result){
                String sUpper = s.toUpperCase();
                String scapteurTag = capteurTag.toString();
                if(s.toUpperCase().equals(capteurTag.toString())){
                    keep = true;
                }
            }
            if(!keep){
                i.remove();
            }
        }
    }

    private List<Capteur2> filterByTypes(ArrayList<String> types_result) {
        List<Capteur2> result= new ArrayList<>();
        result = getAllCapteur2();

        filterListByTypes(types_result, result);
        return result;
    }

    private void filterListByTypes(ArrayList<String> types_result, List<Capteur2> result) {
        Iterator<Capteur2> i = result.iterator();
        while (i.hasNext()) {
            Capteur2 capteur2 = i.next();
            CapteurDataType capteurDataType = capteur2.getData().get(0).getType();
            boolean keep= false;
            for(String s : types_result){
                String sUpper = s.toUpperCase();
                String scapteurDataType = capteurDataType.toString();
                if(s.toUpperCase().equals(capteurDataType.toString())){
                    keep = true;
                }
            }
            if(!keep){
                i.remove();
            }
        }
    }
}
