package Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "capteur", schema = "capteurdb")
public class CapteurEntity {
    private int id;
    private String nom;
    private int id_ville;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nom")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "id_ville")
    public int getIdVille() {
        return id_ville;
    }

    public void setIdVille(int id_ville) {
        this.id_ville = id_ville;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CapteurEntity that = (CapteurEntity) o;
        return id == that.id &&
                Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom);
    }

    @Override
    public String toString() {
        return "\n " +getNom() + "id :" + getId()  + "\n";
    }
}
