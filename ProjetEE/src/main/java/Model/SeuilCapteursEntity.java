package Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "seuilcapteurs", schema = "capteurdb", catalog = "")
public class SeuilCapteursEntity {

    private int id;
    private String type;
    private int value;


    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "value")
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeuilCapteursEntity that = (SeuilCapteursEntity) o;
        return id == that.id && type.equals(that.type) && value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,type,value);
    }

}