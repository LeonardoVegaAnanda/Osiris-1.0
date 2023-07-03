package mx.com.ananda.jemia.osiris.service.implementation;

import lombok.extern.slf4j.Slf4j;
import mx.com.ananda.jemia.osiris.model.dto.PurchaseOrders;
import mx.com.ananda.jemia.osiris.model.dto.VendorPayments;
import mx.com.ananda.jemia.osiris.model.entity.EventoModel;
import mx.com.ananda.jemia.osiris.model.entity.OrdenCompraModel;
import mx.com.ananda.jemia.osiris.model.entity.ProveedorModel;
import mx.com.ananda.jemia.osiris.repositories.IEventoRepository;
import mx.com.ananda.jemia.osiris.repositories.IOrdenCompraRepository;
import mx.com.ananda.jemia.osiris.repositories.IProveedorRepository;
import mx.com.ananda.jemia.osiris.service.interfaces.IOrdenCompraService;
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
public class OrdenServiceImpl implements IOrdenCompraService {

    @Autowired
    private IOrdenCompraRepository iOrden;

    @Autowired
    private IEventoRepository iEvento;

    @Autowired
    private IProveedorRepository iProveedor;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.external.service.base-url}")
    private String basePath;

    @Override
    public List<OrdenCompraModel> findAllOrdenes() {
        return iOrden.findAll();
    }

    @Override
    public Optional<OrdenCompraModel> findOrdenById(Long idOrden) {
        return iOrden.findById(idOrden);
    }

    @Override
    public Optional<OrdenCompraModel> findOrdenByDocNum(Long docNum) {
        //Buscar orden en BD Local
        Optional<OrdenCompraModel> oOrden = iOrden.findByDocNum(docNum);

        if (oOrden.isEmpty()) {
            OrdenCompraModel ordenSAP = ModelMapper.mapearEntidadOrden(restTemplate.getForObject(basePath + "/purchaseJemia/docNum?NumeroOrden=" + docNum, PurchaseOrders.class));
            if (ordenSAP != null) {
                log.info("ORDEN TRAIDA CORRECTAMENTE");
                Optional<ProveedorModel> oProveedor = iProveedor.findByCodigoProveedor(ordenSAP.getCardCode());
                if (oProveedor.isEmpty()) {
                    ProveedorModel proveedorSAP = ModelMapper.mapearEntidadProveedor(restTemplate.getForObject(basePath + "/vendorPayment?cardCode=" + ordenSAP.getCardCode(), VendorPayments.class));
                    if (proveedorSAP != null) {
                        log.info("EXITO AL TRAER EL PROVEEDOR");
                        List<OrdenCompraModel> listaOrdenes = new ArrayList<>();
                        listaOrdenes.add(ordenSAP);
                        proveedorSAP.setListaOrdenes(listaOrdenes);
                        iProveedor.save(proveedorSAP);
                        ordenSAP.setProveedor(proveedorSAP);
                        iOrden.save(ordenSAP);
                        Optional<OrdenCompraModel> ordenTraida = iOrden.findByDocNum(ordenSAP.getDocNum());
                        return ordenTraida;
                    } else {
                        log.error("ERROR Al traer el proveedor");
                        return null;
                    }
                } else {
                    log.info("PROVEEDOR EXISTENTE");
                    ProveedorModel proveedorTraido = oProveedor.get();
                    List<OrdenCompraModel> listaOrdenes = iOrden.findByProveedor_IdProveedor(proveedorTraido.getIdProveedor());
                    listaOrdenes.add(ordenSAP);
                    proveedorTraido.setListaOrdenes(listaOrdenes);
                    iProveedor.save(proveedorTraido);
                    ordenSAP.setProveedor(proveedorTraido);
                    iOrden.save(ordenSAP);
                    Optional<OrdenCompraModel> ordenTraida = iOrden.findByDocNum(ordenSAP.getDocNum());
                    return ordenTraida;
                }
            } else {
                log.error("ORDEN NO TRAIDA, ERROR");
                return null;
            }
        } else {
            return oOrden;
        }
    }

    @Override
    public OrdenCompraModel saveOrden(OrdenCompraModel ordenCompra) {
        return iOrden.save(ordenCompra);
    }

    @Override
    public List<EventoModel> findEventoByOrdenCompra(Long idOrdenCompra) {
        asignarCalificacionOrden(idOrdenCompra);
        return iEvento.findByOrden_IdOrdenCompra(idOrdenCompra);
    }

    @Override
    public void asignarCalificacionOrden(Long idOrdenCompra) {
        List<EventoModel> listaEventos = iEvento.findByOrden_IdOrdenCompra(idOrdenCompra);
        Optional<OrdenCompraModel> oOrden = iOrden.findById(idOrdenCompra);
        OrdenCompraModel ordenTraida = oOrden.get();
        List<EventoModel> listaFormateada = new ArrayList<>();
        if (listaEventos.size() != 0) {
            double calificacionAcumulada = 0;
            for (var evento : listaEventos) {
                calificacionAcumulada += evento.getCalificacionEvento();
                log.info("Calificacion: {}", calificacionAcumulada);
                listaFormateada.add(evento);
                log.info("Contador de eventos con calificacion: {}", listaEventos.size());

            }
            log.info("Size de la lista formateada : {}", listaFormateada.size());
            double promedio = calificacionAcumulada / listaFormateada.size();
            log.info("Promedio : {}", promedio);
            ordenTraida.setCalificacionOrden(promedio);
            iOrden.save(ordenTraida);
        } else {
            ordenTraida.setCalificacionOrden(0.0);
            iOrden.save(ordenTraida);
        }
    }

    @Override
    public Optional<OrdenCompraModel> asignarOrden(Long idOrden, Long numOrden) {
        Optional<OrdenCompraModel> oOrden = iOrden.findById(idOrden);
        if (oOrden.isPresent()) {
            OrdenCompraModel ordenSAP = ModelMapper.mapearEntidadOrden(restTemplate.getForObject(basePath + "/purchaseJemia/docNum?NumeroOrden=" + numOrden, PurchaseOrders.class));
            if (ordenSAP != null) {
                OrdenCompraModel ordenAsignada = oOrden.get();
                ordenAsignada.setDocTotal(ordenSAP.getDocTotal());
                ordenAsignada.setCardName(ordenSAP.getCardName());
                ordenAsignada.setDocDate(ordenSAP.getDocDate());
                ordenAsignada.setDocNum(ordenSAP.getDocNum());
                ordenAsignada.setDocTotal(ordenSAP.getDocTotal());
                ordenAsignada.setCardCode(ordenSAP.getCardCode());
                iOrden.save(ordenAsignada);
                Optional<OrdenCompraModel> ordenDevuelta = iOrden.findById(ordenAsignada.getIdOrdenCompra());
                return ordenDevuelta;
            } else {
                log.error("ERROR, AL TRAER ORDEN");
                return null;
            }

        } else {
            log.error("NO SE PUEDE ASIGNAR UNA ORDEN, INEXISTENTE");
            return null;
        }
    }
}



