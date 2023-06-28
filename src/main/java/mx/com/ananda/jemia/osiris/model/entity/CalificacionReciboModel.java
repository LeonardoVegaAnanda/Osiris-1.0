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

    private double documentoCalif;
    private boolean documentoCalifB;
    private boolean documentoFacturaB;
    private double documentoFacturaCalif;
    private boolean cantidadCalifB;
    private double cantidadCalif;

    private double totalCalif;

    @OneToOne
    private EventoModel evento;
}
