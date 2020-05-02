package be.dto;

import java.io.Serializable;

public class ChangeResponsabilDepartamentDTO implements Serializable {

    private Integer departamentId;
    private String nume;
    private String oldResponsabil;
    private String newResponsabil;

    @Override
    public String toString() {
        return "ChangeResponsabilDepartamentDTO{" +
                "departamentId=" + departamentId +
                ", nume='" + nume + '\'' +
                ", oldResponsabil='" + oldResponsabil + '\'' +
                ", newResponsabil='" + newResponsabil + '\'' +
                '}';
    }

    public Integer getDepartamentId() {
        return departamentId;
    }

    public void setDepartamentId(Integer departamentId) {
        this.departamentId = departamentId;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getOldResponsabil() {
        return oldResponsabil;
    }

    public void setOldResponsabil(String oldResponsabil) {
        this.oldResponsabil = oldResponsabil;
    }

    public String getNewResponsabil() {
        return newResponsabil;
    }

    public void setNewResponsabil(String newResponsabil) {
        this.newResponsabil = newResponsabil;
    }

    public ChangeResponsabilDepartamentDTO() {
    }

    public ChangeResponsabilDepartamentDTO(Integer departamentId, String nume, String oldResponsabil, String newResponsabil) {
        this.departamentId = departamentId;
        this.nume = nume;
        this.oldResponsabil = oldResponsabil;
        this.newResponsabil = newResponsabil;
    }
}
