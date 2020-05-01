package be.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tokens")
public class Token implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

    @Column(name = "token")
    private String token;

    @Column(name = "username")
    private String username;

    @Column(name = "expiration")
    private Date expiration;

    @Override
    public String toString() {
        return "Token{" +
                "ID=" + ID +
                ", token='" + token + '\'' +
                ", username='" + username + '\'' +
                ", expiration=" + expiration +
                '}';
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

    public Token() {
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Token(String token, String username, Date expiration) {
        this.token = token;
        this.username = username;
        this.expiration = expiration;
    }



    public Token(String token, String username) {
        this.token = token;
        this.username = username;
    }
}
