package mx.com.ananda.jemia.osiris.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "defectos_calif")
    private double defectosCalif;

    @Column(name = "condiciones_calif")
    private double condicionesEntregaCalif;

    @Column(name = "verificado_condiciones")
    private boolean condicionesEntregaB;

    @Column(name = "total_calif")
    private double totalCalif;

    @JsonIgnore
    @OneToOne
    private EventoModel evento;
}
