package mx.com.ananda.jemia.osiris.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "evento")
public class EventoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Long idEvento;

    @Column(name = "num_evento")
    private int numEvento;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Column(name = "calificacion_evento")
    private double calificacionEvento;

    @JsonIgnore
    @OneToOne(mappedBy = "evento")
    private CalificacionCalidadModel cCalidad;

    @JsonIgnore
    @OneToOne(mappedBy = "evento")
    private CalificacionComprasModel cCompras;

    @JsonIgnore
    @OneToOne(mappedBy = "evento")
    private CalificacionReciboModel cRecibo;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private OrdenCompraModel orden;

}
