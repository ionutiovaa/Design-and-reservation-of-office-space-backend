/*
package be.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "desks")
public class Desk implements Serializable {

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "desks_utilizari",
            joinColumns = @JoinColumn(name = "desk_id"),
            inverseJoinColumns = @JoinColumn(name = "utilizare_id"))
    private Set<Utilizare> utilizari = new HashSet<>();

    @Override
    public String toString() {
        return "Desk{" +
                "ID=" + ID +
                ", pozitie='" + pozitie + '\'' +
                ", qrCode='" + qrCode + '\'' +
                ", value=" + value +
                ", utilizari=" + utilizari +
                '}';
    }

    public Desk(String pozitie, String qrCode, Integer value, Set<Utilizare> utilizari) {
        this.pozitie = pozitie;
        this.qrCode = qrCode;
        this.value = value;
        this.utilizari = utilizari;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getPozitie() {
        return pozitie;
    }

    public void setPozitie(String pozitie) {
        this.pozitie = pozitie;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Set<Utilizare> getUtilizari() {
        return utilizari;
    }

    public void setUtilizari(Set<Utilizare> utilizari) {
        this.utilizari = utilizari;
    }

    public Desk() {
    }
}
*/
