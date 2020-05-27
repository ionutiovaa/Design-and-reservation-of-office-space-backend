package be.entity;

import be.entity.types.StareLoc;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
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

    /*@Column(name = "stare_loc")
    @Enumerated(EnumType.STRING)
    private StareLoc stareLoc;*/

    /*@OneToOne
    @JoinColumn(name = "pozitie", referencedColumnName = "ID")
    private Pozitie pozitie;*/

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "locuri_utilizari",
            joinColumns = @JoinColumn(name = "loc_id"),
            inverseJoinColumns = @JoinColumn(name = "utilizare_id"))
    private Set<Utilizare> utilizari = new HashSet<>();

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

    public Set<Utilizare> getUtilizari() {
        return utilizari;
    }

    public void setUtilizari(Set<Utilizare> utilizari) {
        this.utilizari = utilizari;
    }

    public Loc() {
    }

    @Override
    public String toString() {
        return "Loc{" +
                "ID=" + ID +
                ", pozitie='" + pozitie + '\'' +
                ", qrCode='" + qrCode + '\'' +
                ", value=" + value +
                ", utilizari=" + utilizari +
                '}';
    }

    public Loc(String pozitie, String qrCode, Integer value, Etaj etaj, Set<Utilizare> utilizari) {
        this.pozitie = pozitie;
        this.qrCode = qrCode;
        this.value = value;
        this.etaj = etaj;
        this.utilizari = utilizari;
    }

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
