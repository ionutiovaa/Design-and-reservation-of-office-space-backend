package be.dto;

import java.io.Serializable;

public class SchedulesDTO implements Serializable {

    private String startTime;
    private String endTime;

    @Override
    public String toString() {
        return "SchedulesDTO{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public SchedulesDTO() {
    }

    public SchedulesDTO(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
