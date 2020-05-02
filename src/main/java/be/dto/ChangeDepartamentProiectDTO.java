package be.dto;

import java.io.Serializable;

public class ChangeDepartamentProiectDTO implements Serializable {

    private Integer id;
    private String nume;
    private String oldDepartament;
    private String newDepartament;

    @Override
    public String toString() {
        return "ChangeDepartamentProiectDTO{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", oldDepartament='" + oldDepartament + '\'' +
                ", newDepartament='" + newDepartament + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getOldDepartament() {
        return oldDepartament;
    }

    public void setOldDepartament(String oldDepartament) {
        this.oldDepartament = oldDepartament;
    }

    public String getNewDepartament() {
        return newDepartament;
    }

    public void setNewDepartament(String newDepartament) {
        this.newDepartament = newDepartament;
    }

    public ChangeDepartamentProiectDTO() {
    }

    public ChangeDepartamentProiectDTO(Integer id, String nume, String oldDepartament, String newDepartament) {
        this.id = id;
        this.nume = nume;
        this.oldDepartament = oldDepartament;
        this.newDepartament = newDepartament;
    }
}
