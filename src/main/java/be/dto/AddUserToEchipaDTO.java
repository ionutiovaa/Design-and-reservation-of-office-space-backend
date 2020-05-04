package be.dto;

import java.io.Serializable;

public class AddUserToEchipaDTO implements Serializable {

    private Integer id;
    private String username;
    private String numeEchipa;

    @Override
    public String toString() {
        return "AddUserToEchipaDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", numeEchipa='" + numeEchipa + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNumeEchipa() {
        return numeEchipa;
    }

    public void setNumeEchipa(String numeEchipa) {
        this.numeEchipa = numeEchipa;
    }

    public AddUserToEchipaDTO() {
    }

    public AddUserToEchipaDTO(Integer id, String username, String numeEchipa) {
        this.id = id;
        this.username = username;
        this.numeEchipa = numeEchipa;
    }
}
