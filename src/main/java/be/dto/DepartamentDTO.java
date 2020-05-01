package be.dto;

import java.io.Serializable;

public class DepartamentDTO implements Serializable {

    private Integer ID;
    private String nume;
    private UserDTO user_responsabil;

    @Override
    public String toString() {
        return "DepartamentDTO{" +
                "ID=" + ID +
                ", nume='" + nume + '\'' +
                ", user_responsabil=" + user_responsabil +
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

    public UserDTO getUser_responsabil() {
        return user_responsabil;
    }

    public void setUser_responsabil(UserDTO user_responsabil) {
        this.user_responsabil = user_responsabil;
    }

    public DepartamentDTO() {
    }

    public DepartamentDTO(Integer ID, String nume, UserDTO user_responsabil) {
        this.ID = ID;
        this.nume = nume;
        this.user_responsabil = user_responsabil;
    }
}
