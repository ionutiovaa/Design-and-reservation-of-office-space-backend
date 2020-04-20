package be.entity;

import be.entity.types.StareRezervare;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rezervari")
public class Rezervare implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "id_initiator", referencedColumnName = "ID")
    private User user;

    @Column(name = "listaOcupanti")
    @OneToMany
    private Set<User> listaOcupanti = new HashSet<>();

    @Column(name = "listaLocuri")
    @OneToMany
    private Set<Loc> listaLocuri = new HashSet<>();

    @Column(name = "descriere")
    private String descriere;

    @Column(name = "stare")
    @Enumerated(EnumType.STRING)
    private StareRezervare stareRezervare;

    @Override
    public String toString() {
        return "Rezervare{" +
                "ID=" + ID +
                ", date=" + date +
                ", user=" + user +
                ", listaOcupanti=" + listaOcupanti +
                ", listaLocuri=" + listaLocuri +
                ", descriere='" + descriere + '\'' +
                ", stareRezervare=" + stareRezervare +
                '}';
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<User> getListaOcupanti() {
        return listaOcupanti;
    }

    public void setListaOcupanti(Set<User> listaOcupanti) {
        this.listaOcupanti = listaOcupanti;
    }

    public Set<Loc> getListaLocuri() {
        return listaLocuri;
    }

    public void setListaLocuri(Set<Loc> listaLocuri) {
        this.listaLocuri = listaLocuri;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public StareRezervare getStareRezervare() {
        return stareRezervare;
    }

    public void setStareRezervare(StareRezervare stareRezervare) {
        this.stareRezervare = stareRezervare;
    }

    public Rezervare() {
    }

    public Rezervare(Date date, User user, Set<User> listaOcupanti, Set<Loc> listaLocuri, String descriere, StareRezervare stareRezervare) {
        this.date = date;
        this.user = user;
        this.listaOcupanti = listaOcupanti;
        this.listaLocuri = listaLocuri;
        this.descriere = descriere;
        this.stareRezervare = stareRezervare;
    }
}
