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
    @Column(name = "id_proveedor")
    private Long idProveedor;

    @Column(name = "nombre_proveedor")
    private String nombreProveedor;

    @Column(name = "codigo_proveedor")
    private String codigoProveedor;

    @Column(name = "calificacion_proveedor")
    private Double calificacionProveedor;

    @Column(name = "calificacion_estatus")
    private String calificacionEstatus;

    @Column(name = "codigo_grupo")
    private int codigoGrupo;

    @Column(name = "telefono_proveedor")
    private String telefonoProveedor;

    @Column(name = "razon_social")
    private String razonSocial;

    @OneToMany(mappedBy = "proveedor")
    private List<OrdenCompraModel> listaOrdenes;

}
