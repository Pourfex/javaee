package emn.DB;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "capteurdata", schema = "capteurdb", catalog = "")
public class CapteurdataEntity {
    private long id;
    private int idCapteur;
    private Timestamp timestamp;
    private Double value;
    private int type;
    private String gps;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "id_capteur")
    public int getIdCapteur() {
        return idCapteur;
    }

    public void setIdCapteur(int idCapteur) {
        this.idCapteur = idCapteur;
    }

    @Basic
    @Column(name = "timestamp")
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Basic
    @Column(name = "value")
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Basic
    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "gps")
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
        return id == that.id &&
                idCapteur == that.idCapteur &&
                type == that.type &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(value, that.value);
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
}
