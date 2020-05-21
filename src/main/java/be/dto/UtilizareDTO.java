package be.dto;

import java.io.Serializable;
import java.util.Date;

public class UtilizareDTO implements Serializable {

    private Integer ID;
    private String username;
    private Integer idLoc;
    private Date startDate;
    private Date finalDate;

    @Override
    public String toString() {
        return "UtilizareDTO{" +
                "ID=" + ID +
                ", username='" + username + '\'' +
                ", idLoc=" + idLoc +
                ", startDate=" + startDate +
                ", finalDate=" + finalDate +
                '}';
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

    public Integer getIdLoc() {
        return idLoc;
    }

    public void setIdLoc(Integer idLoc) {
        this.idLoc = idLoc;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public UtilizareDTO() {
    }

    public UtilizareDTO(Integer ID, String username, Integer idLoc, Date startDate, Date finalDate) {
        this.ID = ID;
        this.username = username;
        this.idLoc = idLoc;
        this.startDate = startDate;
        this.finalDate = finalDate;
    }
}
