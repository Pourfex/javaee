package Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "identifiants", schema = "capteurdb", catalog = "")
public class IdentifiantEntity {


    private String user;
    private String password;


    @Id
    @Column(name = "user")
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentifiantEntity that = (IdentifiantEntity) o;
        return user.equals(that.user) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, user);
    }

    }
