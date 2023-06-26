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

    private double servicioCalif;
    private double tiemposCalif;
    private double totalCalif;

    @OneToOne
    private OrdenCompraModel ordenCompra;
}
