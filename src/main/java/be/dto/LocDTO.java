package be.dto;

import java.io.Serializable;

public class LocDTO implements Serializable {

    private Integer ID;
    private String qrCode;

    public LocDTO() {
    }

    @Override
    public String toString() {
        return "LocDTO{" +
                "ID=" + ID +
                ", qrCode='" + qrCode + '\'' +
                '}';
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public LocDTO(Integer ID, String qrCode) {
        this.ID = ID;
        this.qrCode = qrCode;
    }
}
