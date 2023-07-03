package mx.com.ananda.jemia.osiris.service.implementation;

import lombok.extern.slf4j.Slf4j;
import mx.com.ananda.jemia.osiris.model.dto.VendorPayments;
import mx.com.ananda.jemia.osiris.model.entity.CalificacionEnum;
import mx.com.ananda.jemia.osiris.model.entity.OrdenCompraModel;
import mx.com.ananda.jemia.osiris.model.entity.ProveedorModel;
import mx.com.ananda.jemia.osiris.repositories.IOrdenCompraRepository;
import mx.com.ananda.jemia.osiris.repositories.IProveedorRepository;
import mx.com.ananda.jemia.osiris.service.interfaces.IProveedorService;
import mx.com.ananda.jemia.osiris.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProveedorServiceImpl implements IProveedorService {

    @Autowired
    private IProveedorRepository iProveedor;

    @Autowired
    private IOrdenCompraRepository iOrden;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.external.service.base-url}")
    private String basePath;

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
        Optional<ProveedorModel> oProveedor = iProveedor.findByCodigoProveedor(cardCode);
        if (oProveedor.isEmpty()) {
            ProveedorModel proveedorSAP = ModelMapper.mapearEntidadProveedor(restTemplate.getForObject(basePath + "/vendorPayment?cardCode=" + cardCode, VendorPayments.class));
            if (proveedorSAP != null) {
                log.info("EXITO AL TRAER AL PROVEEDOR");
                iProveedor.save(proveedorSAP);
                Optional<ProveedorModel> proveedorTraido = iProveedor.findByCodigoProveedor(proveedorSAP.getCodigoProveedor());
                return proveedorTraido;
            }
            else{
                log.error("ERROR AL TARER PROVEEDOR");
                return null;
            }
        } else {
            return oProveedor;
        }

    }

    @Override
    public ProveedorModel saveProveedor(ProveedorModel proveedor) {
        return iProveedor.save(proveedor);
    }

    @Override
    public List<OrdenCompraModel> findOrdenesByProveedor(Long idProveedor) {
        asignarCalificacionProveedor(idProveedor);
        return iOrden.findByProveedor_IdProveedor(idProveedor);
    }

    @Override
    public void asignarCalificacionProveedor(Long idProveedor) {
        Optional<ProveedorModel> oProveedor = iProveedor.findById(idProveedor);
        ProveedorModel proveedorTraido = oProveedor.get();
        List<OrdenCompraModel> listaOrdenes = iOrden.findByProveedor_IdProveedor(idProveedor);
        List<OrdenCompraModel> listaFormateada = new ArrayList<>();
        if(listaOrdenes.size()!=0) {
            double calificacionAcumulada = 0;
            for (var ordenes : listaOrdenes) {
                    calificacionAcumulada += ordenes.getCalificacionOrden();
                    listaFormateada.add(ordenes);
                    log.info("Calificacion Acumulada: {}", calificacionAcumulada);

            }
            log.info("Size de la lista: {}",listaFormateada.size());
            double promedio = calificacionAcumulada / listaFormateada.size();
            log.info("promedio: {}",promedio);
            if (promedio < 60) {
                proveedorTraido.setCalificacionProveedor(promedio);
                proveedorTraido.setCalificacionEstatus(String.valueOf(CalificacionEnum.MALO));
            } else if (promedio > 60 && promedio < 80) {
                proveedorTraido.setCalificacionProveedor(promedio);
                proveedorTraido.setCalificacionEstatus(String.valueOf(CalificacionEnum.BUENO));
            } else if (promedio > 80) {
                proveedorTraido.setCalificacionProveedor(promedio);
                proveedorTraido.setCalificacionEstatus(String.valueOf(CalificacionEnum.EXCELENTE));
            }
            iProveedor.save(proveedorTraido);
        }
        else {
            proveedorTraido.setCalificacionProveedor(0.0);
            proveedorTraido.setCalificacionEstatus(String.valueOf(CalificacionEnum.NEUTRO));
            iProveedor.save(proveedorTraido);
        }
    }
}
