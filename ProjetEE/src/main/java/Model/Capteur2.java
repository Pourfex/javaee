package Model;

import MiddleWares.DatabaseMiddleWare;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Capteur2 {

    private String ville;
    private String pays;
    private List<CapteurData> data;
    private CapteurTags tag;


    public Capteur2(CapteurEntity capteurEntity, PaysEntity paysEntity, VilleEntity villeEntity) {
       this.ville = villeEntity.getVille();
       this.pays = paysEntity.getPays();
       this.data = createCapteurDatas(capteurEntity.getId());
       this.tag = createRandomTag();
    }

    private CapteurTags createRandomTag() {
        int random = (new Random().nextInt()) % 5 ;
        switch (random){
            case 0 : return CapteurTags.RAFFINERIE;
            case 1 : return CapteurTags.EXTERIEUR;
            case 2 : return CapteurTags.CHAUFFERIE;
            case 3: return CapteurTags.BUREAUX;
            case 4: return CapteurTags.REACTEURS;
            default: return CapteurTags.RAFFINERIE;
        }
    }

    private List<CapteurData> createCapteurDatas(int id) {
        List<CapteurData> result = new ArrayList<>();

        DatabaseMiddleWare databaseMiddleWare = new DatabaseMiddleWare();
        List<CapteurdataEntity> resultToMap = databaseMiddleWare.getCapteurDataByCapteurId(id);
        for(CapteurdataEntity capteurdataEntity : resultToMap){
            result.add(new CapteurData(capteurdataEntity));
        }

        return result;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public List<CapteurData> getData() {
        return data;
    }

    public void setData(List<CapteurData> data) {
        this.data = data;
    }

    public CapteurTags getTag() {
        return tag;
    }

    public void setTag(CapteurTags tag) {
        this.tag = tag;
    }
}
