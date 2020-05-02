package be.dto;

import java.io.Serializable;

public class ChangeClientProiectDTO implements Serializable {

    private Integer id;
    private String nume;
    private String oldClient;
    private String newClient;

    @Override
    public String toString() {
        return "ChangeClientProiectDTO{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", oldClient='" + oldClient + '\'' +
                ", newClient='" + newClient + '\'' +
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

    public String getOldClient() {
        return oldClient;
    }

    public void setOldClient(String oldClient) {
        this.oldClient = oldClient;
    }

    public String getNewClient() {
        return newClient;
    }

    public void setNewClient(String newClient) {
        this.newClient = newClient;
    }

    public ChangeClientProiectDTO() {
    }

    public ChangeClientProiectDTO(Integer id, String nume, String oldClient, String newClient) {
        this.id = id;
        this.nume = nume;
        this.oldClient = oldClient;
        this.newClient = newClient;
    }
}
