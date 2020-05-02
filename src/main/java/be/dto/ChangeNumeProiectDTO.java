package be.dto;

import java.io.Serializable;

public class ChangeNumeProiectDTO implements Serializable {

    private Integer id;
    private String oldNume;
    private String newNume;

    @Override
    public String toString() {
        return "ChangeNumeProiectDTO{" +
                "id=" + id +
                ", oldNume='" + oldNume + '\'' +
                ", newNume='" + newNume + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public ChangeNumeProiectDTO() {
    }

    public ChangeNumeProiectDTO(Integer id, String oldNume, String newNume) {
        this.id = id;
        this.oldNume = oldNume;
        this.newNume = newNume;
    }
}
