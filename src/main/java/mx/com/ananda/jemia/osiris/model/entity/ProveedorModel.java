package mx.com.ananda.jemia.osiris.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table
@Entity
public class ProveedorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;

    private String nombreProveedor;

    private String codigoProveedor;

    private Double calificacion;

    private String calificacionEstatus;

    @OneToMany(mappedBy = "proveedor")
    private List<OrdenCompraModel> listaOrdenes;

}
