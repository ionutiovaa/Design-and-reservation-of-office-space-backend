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

    @ManyToMany(mappedBy = "utilizari", cascade = CascadeType.ALL)
    private Set<Loc> locuri = new HashSet<>();

    @Override
    public String toString() {
        return "Utilizare{" +
                "ID=" + ID +
                ", user=" + user +
                ", startDate=" + startDate +
                ", finalDate=" + finalDate +
                '}';
    }

    public Utilizare(User user, Date startDate, Date finalDate, Set<Loc> locuri) {
        this.user = user;
        this.startDate = startDate;
        this.finalDate = finalDate;
        this.locuri = locuri;
    }

    public Set<Loc> getLocuri() {
        return locuri;
    }

    public void setLocuri(Set<Loc> locuri) {
        this.locuri = locuri;
    }

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

    public Utilizare(User user, Date startDate, Date finalDate) {
        this.user = user;
        this.startDate = startDate;
        this.finalDate = finalDate;
    }
}
