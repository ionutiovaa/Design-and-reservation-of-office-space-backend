package be.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "dimensions")
    private String dimensions;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Etaj> etaje = new ArrayList<>();

    public Sediu() {
    }

    public Sediu(String nume, String adresa, String dimensions, List<Etaj> etaje) {
        this.nume = nume;
        this.adresa = adresa;
        this.dimensions = dimensions;
        this.etaje = etaje;
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

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public List<Etaj> getEtaje() {
        return etaje;
    }

    public void setEtaje(List<Etaj> etaje) {
        this.etaje = etaje;
    }


}
