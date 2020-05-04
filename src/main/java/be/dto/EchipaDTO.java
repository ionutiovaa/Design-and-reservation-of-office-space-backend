package be.dto;

import java.io.Serializable;
import java.util.Set;

public class EchipaDTO implements Serializable {

    private Integer ID;
    private String nume;
    private Set<ProiectDTO> proiecte;

    @Override
    public String toString() {
        return "EchipaDTO{" +
                "ID=" + ID +
                ", nume='" + nume + '\'' +
                ", proiecte=" + proiecte +
                '}';
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

    public Set<ProiectDTO> getProiecte() {
        return proiecte;
    }

    public void setProiecte(Set<ProiectDTO> proiecte) {
        this.proiecte = proiecte;
    }

    public EchipaDTO() {
    }

    public EchipaDTO(Integer ID, String nume, Set<ProiectDTO> proiecte) {
        this.ID = ID;
        this.nume = nume;
        this.proiecte = proiecte;
    }
}
