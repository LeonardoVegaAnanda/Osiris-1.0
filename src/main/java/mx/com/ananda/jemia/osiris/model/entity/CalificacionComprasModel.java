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

    @Column(name = "servicio_calif")
    private double servicioCalif;

    @Column(name = "tiempo_calif")
    private double tiemposCalif;

    @Column(name = "total_calif")
    private double totalCalif;

    @OneToOne
    private EventoModel evento;
}
