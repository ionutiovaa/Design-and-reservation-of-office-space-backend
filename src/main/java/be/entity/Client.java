package be.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "client")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

    @NotNull
    @Column(name = "nume")
    private String nume;

    @Override
    public String toString() {
        return "Client{" +
                "ID=" + ID +
                ", nume='" + nume + '\'' +
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

    public Client() {
    }

    public Client(String nume) {
        this.nume = nume;
    }
}
