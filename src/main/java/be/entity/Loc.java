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

    @Column(name = "cod_bare")
    private QRCode qrCode;

    @Column(name = "stare_loc")
    @Enumerated(EnumType.STRING)
    private StareLoc stareLoc;

    @OneToOne
    @JoinColumn(name = "pozitie", referencedColumnName = "ID")
    private Pozitie pozitie;

    @OneToMany
    private Set<Utilizare> utilizari = new HashSet<>();

    @Override
    public String toString() {
        return "Loc{" +
                "ID=" + ID +
                ", qrCode=" + qrCode +
                ", stareLoc=" + stareLoc +
                ", pozitie=" + pozitie +
                ", utilizari=" + utilizari +
                '}';
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public QRCode getQrCode() {
        return qrCode;
    }

    public void setQrCode(QRCode qrCode) {
        this.qrCode = qrCode;
    }

    public StareLoc getStareLoc() {
        return stareLoc;
    }

    public void setStareLoc(StareLoc stareLoc) {
        this.stareLoc = stareLoc;
    }

    public Pozitie getPozitie() {
        return pozitie;
    }

    public void setPozitie(Pozitie pozitie) {
        this.pozitie = pozitie;
    }

    public Set<Utilizare> getUtilizari() {
        return utilizari;
    }

    public void setUtilizari(Set<Utilizare> utilizari) {
        this.utilizari = utilizari;
    }

    public Loc() {
    }

    public Loc(QRCode qrCode, StareLoc stareLoc, Pozitie pozitie, Set<Utilizare> utilizari) {
        this.qrCode = qrCode;
        this.stareLoc = stareLoc;
        this.pozitie = pozitie;
        this.utilizari = utilizari;
    }
}