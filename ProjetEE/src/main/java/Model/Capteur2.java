package Model;

import MiddleWares.DatabaseMiddleWare;

import java.util.ArrayList;
import java.util.List;

public class Capteur2 {

    public String ville;
    public String pays;
    public List<CapteurData> data;

    public Capteur2(CapteurEntity capteurEntity, PaysEntity paysEntity, VilleEntity villeEntity) {
       this.ville = villeEntity.getVille();
       this.pays = paysEntity.getPays();

       this.data = createCapteurDatas(capteurEntity.getId());
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
}
