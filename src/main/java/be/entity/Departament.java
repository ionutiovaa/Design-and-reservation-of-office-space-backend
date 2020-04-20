package be.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "departamente")
public class Departament implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

    @Column(name = "nume")
    private String nume;

    @ManyToOne
    @JoinColumn(name = "id_responsabil", referencedColumnName = "ID")
    private User user;

    @Override
    public String toString() {
        return "Departament{" +
                "ID=" + ID +
                ", nume='" + nume + '\'' +
                ", user=" + user +
                '}';
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Departament() {
    }

    public Departament(String nume, User user) {
        this.nume = nume;
        this.user = user;
    }
}
