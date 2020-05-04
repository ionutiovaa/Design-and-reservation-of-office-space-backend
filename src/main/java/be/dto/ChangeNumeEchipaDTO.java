package be.dto;

import java.io.Serializable;

public class ChangeNumeEchipaDTO implements Serializable {

    private Integer id;
    private String oldName;
    private String newName;

    @Override
    public String toString() {
        return "ChangeNumeEchipaDTO{" +
                "id=" + id +
                ", oldName='" + oldName + '\'' +
                ", newName='" + newName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public ChangeNumeEchipaDTO() {
    }

    public ChangeNumeEchipaDTO(Integer id, String oldName, String newName) {
        this.id = id;
        this.oldName = oldName;
        this.newName = newName;
    }
}
