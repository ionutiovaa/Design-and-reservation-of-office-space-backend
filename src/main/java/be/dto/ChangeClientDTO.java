package be.dto;

import java.io.Serializable;

public class ChangeClientDTO implements Serializable {

    private Integer clientId;
    private String oldNume;
    private String newNume;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
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

    public ChangeClientDTO() {
    }

    public ChangeClientDTO(Integer clientId, String oldNume, String newNume) {
        this.clientId = clientId;
        this.oldNume = oldNume;
        this.newNume = newNume;
    }
}
