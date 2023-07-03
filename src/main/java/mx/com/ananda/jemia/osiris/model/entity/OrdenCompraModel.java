package mx.com.ananda.jemia.osiris.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Table(name = "orden_compra")
@Entity
public class OrdenCompraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden_compra")
    private Long idOrdenCompra;

    @Column(name = "numero_orden")
    private Long docNum;

    @Column(name = "fecha_documento")
    private String docDate;

    @Column(name = "total_orden")
    private Double docTotal;

    @Column(name = "codigo_proveedor")
    private String cardCode;

    @Column(name = "nombre_proveedor")
    private String cardName;

    @Column(name = "nota_evento")
    private String notaEvento;

    @Column(name = "calificacion_orden")
    private double calificacionOrden;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private ProveedorModel proveedor;

    @JsonIgnore
    @OneToMany(mappedBy = "orden")
    private List<EventoModel> eventos;
}
