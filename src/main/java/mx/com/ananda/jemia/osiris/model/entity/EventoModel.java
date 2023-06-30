package mx.com.ananda.jemia.osiris.model.entity;

import lombok.Data;

import javax.persistence.*;

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

    @OneToOne(mappedBy = "evento")
    private CalificacionCalidadModel cCalidad;

    @OneToOne(mappedBy = "evento")
    private CalificacionComprasModel cCompras;

    @OneToOne(mappedBy = "evento")
    private CalificacionReciboModel cRecibo;

    @ManyToOne(cascade = CascadeType.ALL)
    private OrdenCompraModel orden;

}
