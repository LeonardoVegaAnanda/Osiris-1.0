package mx.com.ananda.jemia.osiris.service.interfaces;

import mx.com.ananda.jemia.osiris.model.entity.OrdenCompraModel;
import mx.com.ananda.jemia.osiris.model.entity.ProveedorModel;

import java.util.List;
import java.util.Optional;

public interface IProveedorService {

    List<ProveedorModel> findAllProveedores();
    Optional<ProveedorModel> findProveedorById(Long idProveedor);
    Optional<ProveedorModel> findProveedorByCardCode(String cardCode);
    ProveedorModel saveProveedor(ProveedorModel proveedor);

    List<OrdenCompraModel> findOrdenesByProveedor(Long idProveedor);

}
