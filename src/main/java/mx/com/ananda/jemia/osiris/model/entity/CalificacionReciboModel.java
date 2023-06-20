package mx.com.ananda.jemia.osiris.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "calificacion_recibo")
public class CalificacionReciboModel {

    @Id
    @Column(name = "id_calificacion_recibo")
    private Long idCalificacionRecibo;

    private int calif1;
    private int calif2;
    private int calif3;
    private int calif4;
    private int calif5;
    private double totalCalif;

    @OneToOne
    private OrdenCompraModel ordenCompra;
}
