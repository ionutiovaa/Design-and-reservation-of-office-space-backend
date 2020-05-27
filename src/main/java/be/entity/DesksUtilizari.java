/*
package be.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "desks_utilizari")
public class DesksUtilizari implements Serializable {

    public DesksUtilizari() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "desk_id", referencedColumnName = "ID")
    Desk desk;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "utilizare_id", referencedColumnName = "ID")
    Utilizare utilizare;

}
*/
