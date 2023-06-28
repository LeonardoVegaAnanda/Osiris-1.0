package mx.com.ananda.jemia.osiris.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Table(name = "orden_compra")
@Entity
public class OrdenCompraModel {

    @Id
    private Long idOrdenCompra;

    @Column(name = "numero_orden")
    private Long docNum;

    @Column(name = "fecha_documento")
    private String docDate;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @Column(name = "total_orden")
    private Double docTotal;

    @Column(name = "codigo_proveedor")
    private String cardCode;

    @Column(name = "nombre_proveedor")
    private String cardName;


    @ManyToOne(cascade = CascadeType.ALL)
    private ProveedorModel proveedor;

    @OneToMany(mappedBy = "orden")
    private List<EventoModel> eventos;
}
