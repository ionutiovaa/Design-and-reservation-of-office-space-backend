package be.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pozitii")
public class Pozitie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

    @Column(name = "nord")
    private Pozitie nord;

    @Column(name = "est")
    private Pozitie est;

    @Column(name = "vest")
    private Pozitie vest;

    @Column(name = "sud")
    private Pozitie sud;

    @Column(name = "indice")
    private Integer indice;

    @ManyToOne
    @JoinColumn(name = "id_etaj", referencedColumnName = "ID")
    private Etaj etaj;

    @Override
    public String toString() {
        return "Pozitie{" +
                "ID=" + ID +
                ", nord=" + nord +
                ", est=" + est +
                ", vest=" + vest +
                ", sud=" + sud +
                ", indice=" + indice +
                ", etaj=" + etaj +
                '}';
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Pozitie getNord() {
        return nord;
    }

    public void setNord(Pozitie nord) {
        this.nord = nord;
    }

    public Pozitie getEst() {
        return est;
    }

    public void setEst(Pozitie est) {
        this.est = est;
    }

    public Pozitie getVest() {
        return vest;
    }

    public void setVest(Pozitie vest) {
        this.vest = vest;
    }

    public Pozitie getSud() {
        return sud;
    }

    public void setSud(Pozitie sud) {
        this.sud = sud;
    }

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public Etaj getEtaj() {
        return etaj;
    }

    public void setEtaj(Etaj etaj) {
        this.etaj = etaj;
    }

    public Pozitie() {
    }

    public Pozitie(Pozitie nord, Pozitie est, Pozitie vest, Pozitie sud, Integer indice, Etaj etaj) {
        this.nord = nord;
        this.est = est;
        this.vest = vest;
        this.sud = sud;
        this.indice = indice;
        this.etaj = etaj;
    }
}
