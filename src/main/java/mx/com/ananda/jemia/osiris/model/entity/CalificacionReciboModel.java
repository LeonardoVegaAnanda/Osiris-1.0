package mx.com.ananda.jemia.osiris.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "calificacion_recibo")
public class CalificacionReciboModel {

    @Id
    @Column(name = "id_calificacion_recibo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCalificacionRecibo;

    @Column(name = "documento_calif")
    private double documentoCalif;

    @Column(name = "verificado_documento")
    private boolean documentoCalifB;

    @Column(name = "verificado_factura")
    private boolean documentoFacturaB;

    @Column(name = "factura_calif")
    private double documentoFacturaCalif;

    @Column(name = "verificado_cantidad")
    private boolean cantidadCalifB;

    @Column(name = "cantidad_calif")
    private double cantidadCalif;

    @Column(name = "total_calif")
    private double totalCalif;

    @OneToOne
    private EventoModel evento;
}
