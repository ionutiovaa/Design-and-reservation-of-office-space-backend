package be.dto;

import java.io.Serializable;

public class ProiectDTO implements Serializable {

    private Integer ID;
    private String nume;
    private ClientDTO client;
    private DepartamentDTO departament;

    @Override
    public String toString() {
        return "ProiectDTO{" +
                "ID=" + ID +
                ", nume='" + nume + '\'' +
                ", client=" + client +
                ", departament=" + departament +
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

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public DepartamentDTO getDepartament() {
        return departament;
    }

    public void setDepartament(DepartamentDTO departament) {
        this.departament = departament;
    }

    public ProiectDTO() {
    }

    public ProiectDTO(Integer ID, String nume, ClientDTO client, DepartamentDTO departament) {
        this.ID = ID;
        this.nume = nume;
        this.client = client;
        this.departament = departament;
    }
}
