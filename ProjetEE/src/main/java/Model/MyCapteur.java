package Model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "capteurdata", schema = "capteurdb")
@SecondaryTables({
        @SecondaryTable(name="capteur"),
        @SecondaryTable(name="ville"),
        @SecondaryTable(name="pays") })

public class MyCapteur {
    private long id;
    private int idCapteur;
    private Timestamp timestamp;
    private Double value;
    private int type;
    private String gps;
    private String nom;
    private String ville;
    private String pays;



    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "id_capteur", nullable = false)
    public int getIdCapteur() {
        return idCapteur;
    }

    public void setIdCapteur(int idCapteur) {
        this.idCapteur = idCapteur;
    }

    @Basic
    @Column(name = "timestamp", nullable = false)
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Basic
    @Column(name = "value", precision = 0)
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "gps", nullable = false, length = 100)
    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CapteurdataEntity that = (CapteurdataEntity) o;

        return id == that.getId() &&
                idCapteur == that.getIdCapteur() &&
                type == that.getType() &&
                Objects.equals(timestamp, that.getTimestamp()) &&
                Objects.equals(value, that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idCapteur, timestamp, value, type);
    }

    @Override
    public String toString() {
        return  "\n CapteurData id : " + getId()
                + "\n capteur id : " + getIdCapteur()
                + "\n type : " + getType()
                + "\n value : " + getValue()
                + "\n timestamp : " + getTimestamp();
    }

    @Basic
    @Column(name = "nom", nullable = false, table="capteur")
    public String getNom() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "ville", nullable = false, table="ville")
    public String getVille() {
        return ville;
    }


    public void setVille(String ville) {
        this.ville = ville;
    }

    @Basic
    @Column(name = "pays", nullable = false, table="pays")
    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }
}