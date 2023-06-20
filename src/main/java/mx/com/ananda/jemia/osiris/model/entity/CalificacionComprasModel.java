package mx.com.ananda.jemia.osiris.model.entity;

import lombok.Data;


import javax.persistence.*;

@Data
@Table(name = "calificacion_compras")
@Entity
public class CalificacionComprasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_calificacion_compras")
    private Long idCalificacionCompras;

    private int calif1;
    private int calif2;
    private int calif3;
    private int calif4;
    private int calif5;
    private double totalCalif;

    @OneToOne
    private OrdenCompraModel ordenCompra;
}
