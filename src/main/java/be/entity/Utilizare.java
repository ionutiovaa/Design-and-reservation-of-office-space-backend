package be.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "utilizari")
public class Utilizare implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

    @OneToOne
    @JoinColumn(name = "user", referencedColumnName = "ID")
    private User user;

    private Date startDate;

    private Date finalDate;

    @ManyToOne
    @JoinColumn(name = "id_loc", referencedColumnName = "ID")
    private Loc loc;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Utilizare() {
    }

    public Utilizare(User user, Date startDate, Date finalDate, Loc loc) {
        this.user = user;
        this.startDate = startDate;
        this.finalDate = finalDate;
        this.loc = loc;
    }
}
