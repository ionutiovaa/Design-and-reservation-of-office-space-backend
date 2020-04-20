package be.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "qrcode")
public class QRCode implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

}
