package be.dto;

import java.io.Serializable;

public class ClientDTO implements Serializable {

    private Integer ID;
    private String nume;

    @Override
    public String toString() {
        return "ClientDTO{" +
                "ID=" + ID +
                ", nume='" + nume + '\'' +
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

    public ClientDTO() {
    }

    public ClientDTO(Integer ID, String nume) {
        this.ID = ID;
        this.nume = nume;
    }
}
