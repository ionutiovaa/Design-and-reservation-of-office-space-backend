package be.entity;

import be.entity.types.StareLoc;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "locuri")
public class Loc implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

    @Column(name = "pozitie")
    private String pozitie;

    @Column(name = "qrCode")
    private String qrCode;

    @Column(name = "value")
    private Integer value;

    @ManyToOne
    @JoinColumn(name = "id_etaj", referencedColumnName = "ID")
    private Etaj etaj;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Utilizare> utilizari = new ArrayList<>();

    public Loc(String pozitie, String qrCode, Integer value, Etaj etaj, List<Utilizare> utilizari) {
        this.pozitie = pozitie;
        this.qrCode = qrCode;
        this.value = value;
        this.etaj = etaj;
        this.utilizari = utilizari;
    }

    /*@Column(name = "stare_loc")
    @Enumerated(EnumType.STRING)
    private StareLoc stareLoc;*/

    /*@OneToOne
    @JoinColumn(name = "pozitie", referencedColumnName = "ID")
    private Pozitie pozitie;*/

    /*@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "locuri_utilizari",
            joinColumns = @JoinColumn(name = "loc_id"),
            inverseJoinColumns = @JoinColumn(name = "utilizare_id"))
    private Set<Utilizare> utilizari = new HashSet<>();*/

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    /*public Set<Utilizare> getUtilizari() {
        return utilizari;
    }

    public void setUtilizari(Set<Utilizare> utilizari) {
        this.utilizari = utilizari;
    }*/

    public Loc() {
    }

    public Etaj getEtaj() {
        return etaj;
    }

    public void setEtaj(Etaj etaj) {
        this.etaj = etaj;
    }

    public List<Utilizare> getUtilizari() {
        return utilizari;
    }

    public void setUtilizari(List<Utilizare> utilizari) {
        this.utilizari = utilizari;
    }

    /*public Loc(String pozitie, String qrCode, Integer value, Etaj etaj, Set<Utilizare> utilizari) {
        this.pozitie = pozitie;
        this.qrCode = qrCode;
        this.value = value;
        this.etaj = etaj;
        this.utilizari = utilizari;
    }*/

    public String getPozitie() {
        return pozitie;
    }

    public void setPozitie(String pozitie) {
        this.pozitie = pozitie;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
