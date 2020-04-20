package be.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sedii")
public class Sediu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

    @Column(name = "nume")
    private String nume;

    @Column(name = "adresa")
    private String adresa;

    public Sediu() {
    }

    public Sediu(String nume, String adresa) {
        this.nume = nume;
        this.adresa = adresa;
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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "Sediu{" +
                "ID=" + ID +
                ", nume='" + nume + '\'' +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}
