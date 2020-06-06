package be.dto;

import java.io.Serializable;
import java.util.Date;

public class UtilizareDTO implements Serializable {

    private Integer ID;
    private String username;
    private String position;
    private String startDate;
    private String finalDate;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public UtilizareDTO(Integer ID, String username, String position, String startDate, String finalDate) {
        this.ID = ID;
        this.username = username;
        this.position = position;
        this.startDate = startDate;
        this.finalDate = finalDate;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(String finalDate) {
        this.finalDate = finalDate;
    }

    public UtilizareDTO() {
    }


}
