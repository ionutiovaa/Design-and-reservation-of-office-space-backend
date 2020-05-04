package be.dto;

import java.io.Serializable;

public class AddProiectToEchipaDTO implements Serializable {

    private Integer id;
    private String numeEchipa;
    private String numeProiect;

    @Override
    public String toString() {
        return "AddProiectToEchipaDTO{" +
                "id=" + id +
                ", numeEchipa='" + numeEchipa + '\'' +
                ", numeProiect='" + numeProiect + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeEchipa() {
        return numeEchipa;
    }

    public void setNumeEchipa(String numeEchipa) {
        this.numeEchipa = numeEchipa;
    }

    public String getNumeProiect() {
        return numeProiect;
    }

    public void setNumeProiect(String numeProiect) {
        this.numeProiect = numeProiect;
    }

    public AddProiectToEchipaDTO() {
    }

    public AddProiectToEchipaDTO(Integer id, String numeEchipa, String numeProiect) {
        this.id = id;
        this.numeEchipa = numeEchipa;
        this.numeProiect = numeProiect;
    }
}
