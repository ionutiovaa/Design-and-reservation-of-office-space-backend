package be.dto;

import java.io.Serializable;
import java.sql.Time;

public class FreeTimeDTO implements Serializable {

    private Time start_date;
    private Time final_date;

    @Override
    public String toString() {
        return "FreeTimeDTO{" +
                "start_date=" + start_date +
                ", final_date=" + final_date +
                '}';
    }

    public FreeTimeDTO() {
    }

    public Time getStart_date() {
        return start_date;
    }

    public void setStart_date(Time start_date) {
        this.start_date = start_date;
    }

    public Time getFinal_date() {
        return final_date;
    }

    public void setFinal_date(Time final_date) {
        this.final_date = final_date;
    }

    public FreeTimeDTO(Time start_date, Time final_date) {
        this.start_date = start_date;
        this.final_date = final_date;
    }
}
