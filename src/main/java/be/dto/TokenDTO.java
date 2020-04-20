package be.dto;

import java.io.Serializable;

public class TokenDTO implements Serializable {

    private Integer ID;
    private String token;
    private String username;
    private String password;

    public TokenDTO(String token, String username, String password) {
        this.token = token;
        this.username = username;
        this.password = password;
    }

    public TokenDTO() {
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
