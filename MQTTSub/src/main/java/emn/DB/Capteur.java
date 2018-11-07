package emn.DB;

public class Capteur {

    private CapteurEntity capteurEntity;
    private PaysEntity paysEntity;
    private VilleEntity villeEntity;

    public Capteur(CapteurEntity capteurEntity, PaysEntity paysEntity, VilleEntity villeEntity) {
        this.capteurEntity = capteurEntity;
        this.paysEntity = paysEntity;
        this.villeEntity = villeEntity;
    }

    public CapteurEntity getCapteurEntity() {
        return capteurEntity;
    }

    public void setCapteurEntity(CapteurEntity capteurEntity) {
        this.capteurEntity = capteurEntity;
    }

    public PaysEntity getPaysEntity() {
        return paysEntity;
    }

    public void setPaysEntity(PaysEntity paysEntity) {
        this.paysEntity = paysEntity;
    }

    public VilleEntity getVilleEntity() {
        return villeEntity;
    }

    public void setVilleEntity(VilleEntity villeEntity) {
        this.villeEntity = villeEntity;
    }

    @Override
    public String toString() {
        return "\n " +
                "////////////////////////////////////////////  CAPTEUR //////////////////////////////////////////////" +
                capteurEntity.toString() + "\n " +
                paysEntity.toString() + "\n " +
                villeEntity.toString() + "\n " + 
                "\n " ;
    }
}
