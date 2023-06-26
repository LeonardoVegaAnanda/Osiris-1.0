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

    private double defectosCalif;
    private double condicionesEntregaCalif;
    private double condicionesEntregaB;
    private double totalCalif;

    @OneToOne
    private OrdenCompraModel ordenCompra;
}
