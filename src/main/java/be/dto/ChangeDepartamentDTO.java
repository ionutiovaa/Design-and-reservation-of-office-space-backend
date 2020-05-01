package be.dto;

import java.io.Serializable;

public class ChangeDepartamentDTO implements Serializable {

    private Integer departamentId;
    private String oldNume;
    private String newNume;

    @Override
    public String toString() {
        return "ChangeDepartamentDTO{" +
                "departamentId=" + departamentId +
                ", oldNume='" + oldNume + '\'' +
                ", newNume='" + newNume + '\'' +
                '}';
    }

    public Integer getDepartamentId() {
        return departamentId;
    }

    public void setDepartamentId(Integer departamentId) {
        this.departamentId = departamentId;
    }

    public String getOldNume() {
        return oldNume;
    }

    public void setOldNume(String oldNume) {
        this.oldNume = oldNume;
    }

    public String getNewNume() {
        return newNume;
    }

    public void setNewNume(String newNume) {
        this.newNume = newNume;
    }

    public ChangeDepartamentDTO() {
    }

    public ChangeDepartamentDTO(Integer departamentId, String oldNume, String newNume) {
        this.departamentId = departamentId;
        this.oldNume = oldNume;
        this.newNume = newNume;
    }
}
