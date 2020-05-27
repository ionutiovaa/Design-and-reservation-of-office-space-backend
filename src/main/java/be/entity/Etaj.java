package be.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "etaje")
public class Etaj implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

    @Column(name = "numar")
    private Integer numar;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Loc> locuri = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_sediu", referencedColumnName = "ID")
    private Sediu sediu;

    /*@Override
    public String toString() {
        return "Etaj{" +
                "ID=" + ID +
                ", numar=" + numar +
                ", locuri=" + locuri +
                ", sediu=" + sediu +
                '}';
    }*/

    public List<Loc> getLocuri() {
        return locuri;
    }

    public void setLocuri(List<Loc> locuri) {
        this.locuri = locuri;
    }

    public Etaj(Integer numar, List<Loc> locuri, Sediu sediu) {
        this.numar = numar;
        this.locuri = locuri;
        this.sediu = sediu;
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

    public Sediu getSediu() {
        return sediu;
    }

    public void setSediu(Sediu sediu) {
        this.sediu = sediu;
    }

    public Etaj() {
    }
}

