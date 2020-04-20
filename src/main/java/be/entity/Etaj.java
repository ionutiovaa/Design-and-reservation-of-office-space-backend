package be.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "etaje")
public class Etaj implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

    @Column(name = "numar")
    private Integer numar;

    @Column(name = "nume")
    private String nume;

    @ManyToOne
    @JoinColumn(name = "id_sediu", referencedColumnName = "ID")
    private Sediu sediu;

    @Override
    public String toString() {
        return "Etaj{" +
                "ID=" + ID +
                ", numar=" + numar +
                ", nume='" + nume + '\'' +
                ", sediu=" + sediu +
                '}';
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getNumar() {
        return numar;
    }

    public void setNumar(Integer numar) {
        this.numar = numar;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Sediu getSediu() {
        return sediu;
    }

    public void setSediu(Sediu sediu) {
        this.sediu = sediu;
    }

    public Etaj() {
    }

    public Etaj(Integer numar, String nume, Sediu sediu) {
        this.numar = numar;
        this.nume = nume;
        this.sediu = sediu;
    }
}

