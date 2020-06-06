package be.dto;

import java.io.Serializable;

public class ForGetSchedulesDTO implements Serializable {

    private String date;
    private String position;

    @Override
    public String toString() {
        return "ForGetSchedulesDTO{" +
                "date='" + date + '\'' +
                ", position='" + position + '\'' +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public ForGetSchedulesDTO() {
    }

    public ForGetSchedulesDTO(String date, String position) {
        this.date = date;
        this.position = position;
    }
}
