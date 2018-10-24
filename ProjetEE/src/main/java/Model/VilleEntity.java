package Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ville", schema = "capteurdb", catalog = "")
public class VilleEntity {
    private int id;
    private String ville;
    private int id_pays;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ville")
    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Basic
    @Column(name = "id_pays")
    public int getIdPays() {
        return id_pays;
    }

    public void setIdPays(int id_pays) {
        this.id_pays = id_pays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VilleEntity that = (VilleEntity) o;
        return id == that.id && ville.equals(that.ville) && id_pays == that.id_pays;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ville, id_pays);
    }

    @Override
    public String toString() {
        return getVille() + "id :" + getId() + " id_pays :" + getIdPays();
    }
}
