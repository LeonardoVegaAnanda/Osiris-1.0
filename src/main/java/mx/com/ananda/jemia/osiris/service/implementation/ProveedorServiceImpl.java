package mx.com.ananda.jemia.osiris.service.implementation;

import mx.com.ananda.jemia.osiris.model.entity.OrdenCompraModel;
import mx.com.ananda.jemia.osiris.model.entity.ProveedorModel;
import mx.com.ananda.jemia.osiris.repositories.IOrdenCompraRepository;
import mx.com.ananda.jemia.osiris.repositories.IProveedorRepository;
import mx.com.ananda.jemia.osiris.service.interfaces.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImpl implements IProveedorService {

    @Autowired
    private IProveedorRepository iProveedor;

    @Autowired
    private IOrdenCompraRepository iOrden;

    @Override
    public List<ProveedorModel> findAllProveedores() {
        return iProveedor.findAll();
    }

    @Override
    public Optional<ProveedorModel> findProveedorById(Long idProveedor) {
        return iProveedor.findById(idProveedor);
    }

    @Override
    public Optional<ProveedorModel> findProveedorByCardCode(String cardCode) {
        return iProveedor.findByCodigoProveedor(cardCode);
    }

    @Override
    public ProveedorModel saveProveedor(ProveedorModel proveedor) {
        return iProveedor.save(proveedor);
    }

    @Override
    public List<OrdenCompraModel> findOrdenesByProveedor(Long idProveedor) {
        return iOrden.findByProveedor_IdProveedor(idProveedor);
    }
}
