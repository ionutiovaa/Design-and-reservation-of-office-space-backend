package be.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "echipa")
public class Echipa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

    @Column(name = "nume")
    private String nume;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "echipa_proiecte",
            joinColumns = @JoinColumn(name = "echipa_id"),
            inverseJoinColumns = @JoinColumn(name = "proiecte_id"))
    private Set<Proiect> proiecte = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_echipe",
            joinColumns = @JoinColumn(name = "echipe_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Echipa{" +
                "ID=" + ID +
                ", nume='" + nume + '\'' +
                ", proiecte=" + proiecte +
                ", users=" + users +
                '}';
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Set<Proiect> getProiecte() {
        return proiecte;
    }

    public void setProiecte(Set<Proiect> proiecte) {
        this.proiecte = proiecte;
    }

    public Echipa() {
    }

    public Echipa(String nume, Set<Proiect> proiecte, Set<User> users) {
        this.nume = nume;
        this.proiecte = proiecte;
        this.users = users;
    }
}
