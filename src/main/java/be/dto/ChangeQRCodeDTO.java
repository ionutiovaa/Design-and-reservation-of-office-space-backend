package be.dto;

import java.io.Serializable;

public class ChangeQRCodeDTO implements Serializable {

    private Integer qrcodeid;
    private String oldNume;
    private String newNume;

    @Override
    public String toString() {
        return "ChangeQRCodeDTO{" +
                "qrcodeid=" + qrcodeid +
                ", oldNume='" + oldNume + '\'' +
                ", newNume='" + newNume + '\'' +
                '}';
    }

    public Integer getQrcodeid() {
        return qrcodeid;
    }

    public void setQrcodeid(Integer qrcodeid) {
        this.qrcodeid = qrcodeid;
    }

    public String getOldNume() {
        return oldNume;
    }

    public void setOldNume(String oldNume) {
        this.oldNume = oldNume;
    }

    public String getNewNume() {
        return newNume;
    }

    public void setNewNume(String newNume) {
        this.newNume = newNume;
    }

    public ChangeQRCodeDTO() {
    }

    public ChangeQRCodeDTO(Integer qrcodeid, String oldNume, String newNume) {
        this.qrcodeid = qrcodeid;
        this.oldNume = oldNume;
        this.newNume = newNume;
    }
}
