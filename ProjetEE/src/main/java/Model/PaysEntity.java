package Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pays", schema = "capteurdb", catalog = "")
public class PaysEntity {
    private int id;
    private String pays;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pays")
    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaysEntity that = (PaysEntity) o;
        return id == that.id && pays.equals(that.pays);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, pays);
    }

    @Override
    public String toString() {
        return getPays() + "id :" + getId();
    }
}
