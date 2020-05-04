package be.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "proiecte")
public class Proiect implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

    @Column(name = "nume")
    private String nume;

    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "ID")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_departament", referencedColumnName = "ID")
    private Departament departament;

    @ManyToMany(mappedBy = "proiecte", cascade = CascadeType.ALL)
    private Set<Echipa> echipe = new HashSet<>();

    @Override
    public String toString() {
        return "Proiect{" +
                "ID=" + ID +
                ", nume='" + nume + '\'' +
                ", client=" + client +
                ", departament=" + departament +
                ", echipe=" + echipe +
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Departament getDepartament() {
        return departament;
    }

    public void setDepartament(Departament departament) {
        this.departament = departament;
    }

    public Set<Echipa> getEchipe() {
        return echipe;
    }

    public void setEchipe(Set<Echipa> echipe) {
        this.echipe = echipe;
    }

    public Proiect() {
    }

    public Proiect(String nume, Client client, Departament departament) {
        this.nume = nume;
        this.client = client;
        this.departament = departament;
    }

    public Proiect(String nume, Client client, Departament departament, Set<Echipa> echipe) {
        this.nume = nume;
        this.client = client;
        this.departament = departament;
        this.echipe = echipe;
    }
}
