package be.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "locuri_utilizari")
public class LocuriUtilizari implements Serializable {

    public LocuriUtilizari() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "loc_id", referencedColumnName = "ID")
    Loc loc;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "utilizare_id", referencedColumnName = "ID")
    Utilizare utilizare;

    @Override
    public String toString() {
        return "LocuriUtilizari{" +
                "ID=" + ID +
                ", loc=" + loc +
                ", utilizare=" + utilizare +
                '}';
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Loc getLoc() {
        return loc;
    }

    public void setLoc(Loc loc) {
        this.loc = loc;
    }

    public Utilizare getUtilizare() {
        return utilizare;
    }

    public void setUtilizare(Utilizare utilizare) {
        this.utilizare = utilizare;
    }

    public LocuriUtilizari(Loc loc, Utilizare utilizare) {
        this.loc = loc;
        this.utilizare = utilizare;
    }
}
