package mx.com.ananda.jemia.osiris.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "calificacion_calidad")
@Entity
public class CalificacionCalidadModel {

    @Id
    @Column(name = "id_calificacion_calidad")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCalificacionCalidad;

    private int calif1;
    private int calif2;
    private int calif3;
    private int calif4;
    private int calif5;
    private double totalCalif;

    @OneToOne
    private OrdenCompraModel ordenCompra;
}
