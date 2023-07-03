package mx.com.ananda.jemia.osiris.service.implementation;

import lombok.extern.slf4j.Slf4j;
import mx.com.ananda.jemia.osiris.model.dto.PurchaseOrders;
import mx.com.ananda.jemia.osiris.model.dto.VendorPayments;
import mx.com.ananda.jemia.osiris.model.entity.*;
import mx.com.ananda.jemia.osiris.repositories.*;
import mx.com.ananda.jemia.osiris.service.interfaces.ICalificacionReciboService;
import mx.com.ananda.jemia.osiris.service.interfaces.IEventoService;
import mx.com.ananda.jemia.osiris.util.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EventoServiceImpl implements IEventoService {

    @Autowired
    private IEventoRepository iEvento;

    @Autowired
    private ICalificacionCalidadRepository iCalidad;

    @Autowired
    private ICalificacionComprasRepository iCompras;

    @Autowired
    private ICalificacionReciboRepository iRecibo;

    @Autowired
    private IOrdenCompraRepository iOrden;

    @Autowired
    private IProveedorRepository iProveedor;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.external.service.base-url}")
    private String basePath;


    @Override
    public List<EventoModel> findAlleventos() {
        return iEvento.findAll();
    }

    @Override
    public Optional<EventoModel> findEventoModelById(Long idEvento) {
        return iEvento.findById(idEvento);
    }

    @Override
    public void updateEvento(EventoModel evento) {
        iEvento.save(evento);
    }

    @Override
    public EventoModel saveEventoDocNum(Long docNum) {

        //Buscar orden desde BD Local
        Optional<OrdenCompraModel> oOrden = iOrden.findByDocNum(docNum);
        if (oOrden.isEmpty()) {

            //Buscar orden desde SAP
            OrdenCompraModel ordenSAP = ModelMapper.mapearEntidadOrden(restTemplate.getForObject(basePath + "/purchaseJemia/docNum?NumeroOrden=" + docNum, PurchaseOrders.class));
            if (ordenSAP != null) {
                log.info("ORDEN TRAIDA DESDE SAP, CORRECTAMENTE");

                //Buscar proveedor BD
                Optional<ProveedorModel> oProveedor = iProveedor.findByCodigoProveedor(ordenSAP.getCardCode());
                if (oProveedor.isEmpty()) {

                    //Buscar proveedor desde SAP
                    ProveedorModel proveedorSAP = ModelMapper.mapearEntidadProveedor(restTemplate.getForObject(basePath + "/vendorPayment?cardCode=" + ordenSAP.getCardCode(), VendorPayments.class));
                    if (proveedorSAP != null) {
                        log.info("PROVEEDOR TRAIDO CORRECTAMENTE");
                        List<EventoModel> listaEventosOrden = new ArrayList<>();
                        List<OrdenCompraModel> listaOrdenes = new ArrayList<>();
                        int contador = 0;
                        EventoModel eventoNuevo = new EventoModel();
                        eventoNuevo.setNumEvento(contador + 1);
                        eventoNuevo.setOrden(ordenSAP);
                        eventoNuevo.setFechaIngreso(LocalDate.now());
                        listaOrdenes.add(ordenSAP);
                        proveedorSAP.setListaOrdenes(listaOrdenes);
                        iProveedor.save(proveedorSAP);
                        ordenSAP.setProveedor(proveedorSAP);
                        listaEventosOrden.add(eventoNuevo);
                        ordenSAP.setEventos(listaEventosOrden);
                        iOrden.save(ordenSAP);
                        iEvento.save(eventoNuevo);
                        return eventoNuevo;
                    } else {
                        log.error("PROVEEDOR NO ENCONTRADO, ERROR");
                        return null;
                    }
                } else {
                    log.info("PROVEEDOR EXISTENTE");
                    ProveedorModel provedorTraido = oProveedor.get();
                    List<EventoModel> listaEventosOrden = new ArrayList<>();
                    List<OrdenCompraModel> listaOrdenes = iOrden.findByProveedor_IdProveedor(provedorTraido.getIdProveedor());
                    int contador = 0;
                    EventoModel eventoNuevo = new EventoModel();
                    eventoNuevo.setNumEvento(contador + 1);
                    eventoNuevo.setOrden(ordenSAP);
                    eventoNuevo.setFechaIngreso(LocalDate.now());
                    listaOrdenes.add(ordenSAP);
                    provedorTraido.setListaOrdenes(listaOrdenes);
                    iProveedor.save(provedorTraido);
                    ordenSAP.setProveedor(provedorTraido);
                    listaEventosOrden.add(eventoNuevo);
                    ordenSAP.setEventos(listaEventosOrden);
                    iOrden.save(ordenSAP);
                    iEvento.save(eventoNuevo);
                    return eventoNuevo;
                }
            } else {
                log.error("ORDEN NO TRAIDA, ERROR");
                return null;
            }
        } else {
            OrdenCompraModel ordenTraidaBD = oOrden.get();
            Optional<ProveedorModel> oProveedor = iProveedor.findByCodigoProveedor(ordenTraidaBD.getCardCode());
            ProveedorModel proveedorBD = oProveedor.get();
            List<EventoModel> listaEventosOrden = iEvento.findByOrden_DocNum(ordenTraidaBD.getDocNum());
            if (listaEventosOrden.size() != 0) {
                log.info("LA LISTA CONTIENE: {}", listaEventosOrden.size());
                int contador = listaEventosOrden.size();
                EventoModel eventoNuevo = new EventoModel();
                eventoNuevo.setNumEvento(contador + 1);
                eventoNuevo.setOrden(ordenTraidaBD);
                eventoNuevo.setFechaIngreso(LocalDate.now());
                listaEventosOrden.add(eventoNuevo);
                iEvento.save(eventoNuevo);
                ordenTraidaBD.setEventos(listaEventosOrden);
                ordenTraidaBD.setProveedor(proveedorBD);
                iOrden.save(ordenTraidaBD);
                return eventoNuevo;
            }
            else{
                log.info("LA LISTA CONTIENE: {}", listaEventosOrden.size());
                int contador = listaEventosOrden.size();
                EventoModel eventoNuevo = new EventoModel();
                eventoNuevo.setNumEvento(contador + 1);
                eventoNuevo.setOrden(ordenTraidaBD);
                eventoNuevo.setFechaIngreso(LocalDate.now());
                listaEventosOrden.add(eventoNuevo);
                iEvento.save(eventoNuevo);
                ordenTraidaBD.setEventos(listaEventosOrden);
                ordenTraidaBD.setProveedor(proveedorBD);
                iOrden.save(ordenTraidaBD);
                return eventoNuevo;
            }
        }
    }

    @Override
    public EventoModel saveEventoCardCode(String cardCode, String notaEvento) {

        //Buscar orden en la BD Local
        Optional<OrdenCompraModel> oOrden = iOrden.findByNotaEvento(notaEvento);
        if (oOrden.isEmpty()) {
            log.info("ORDEN NUEVA");
            OrdenCompraModel ordenNueva = new OrdenCompraModel();
            //Buscar proveedor en la BD Local
            Optional<ProveedorModel> oProveedor = iProveedor.findByCodigoProveedor(cardCode);
            if (oProveedor.isEmpty()) {

                //Buscar proveedor desde SAP
                ProveedorModel proveedorSAP = ModelMapper.mapearEntidadProveedor(restTemplate.getForObject(basePath + "/vendorPayment?cardCode=" + cardCode, VendorPayments.class));
                if (proveedorSAP != null) {
                    log.info("PROVEEDOR TRAIDO CORRECTAMENTE");
                    List<EventoModel> listaEventosOrden = new ArrayList<>();
                    List<OrdenCompraModel> listaOrdenes = new ArrayList<>();
                    int contador = 0;
                    EventoModel eventoNuevo = new EventoModel();
                    eventoNuevo.setNumEvento(contador + 1);
                    eventoNuevo.setOrden(ordenNueva);
                    eventoNuevo.setFechaIngreso(LocalDate.now());
                    listaOrdenes.add(ordenNueva);
                    proveedorSAP.setListaOrdenes(listaOrdenes);
                    iProveedor.save(proveedorSAP);
                    ordenNueva.setProveedor(proveedorSAP);
                    ordenNueva.setNotaEvento(notaEvento);
                    ordenNueva.setCardCode(proveedorSAP.getCodigoProveedor());
                    listaEventosOrden.add(eventoNuevo);
                    ordenNueva.setEventos(listaEventosOrden);
                    iOrden.save(ordenNueva);
                    iEvento.save(eventoNuevo);
                    return eventoNuevo;
                }
                else {
                    log.error("NO SE HA ENCONTRADO AL PROVEEDOR");
                    return null;
                }
            }
            else{
                log.info("PROVEEDOR EXISTENTE");
                ProveedorModel provedorTraido = oProveedor.get();
                List<EventoModel> listaEventosOrden = new ArrayList<>();
                List<OrdenCompraModel> listaOrdenes = iOrden.findByProveedor_IdProveedor(provedorTraido.getIdProveedor());
                int contador = 0;
                EventoModel eventoNuevo = new EventoModel();
                eventoNuevo.setNumEvento(contador + 1);
                eventoNuevo.setOrden(ordenNueva);
                eventoNuevo.setFechaIngreso(LocalDate.now());
                listaOrdenes.add(ordenNueva);
                provedorTraido.setListaOrdenes(listaOrdenes);
                iProveedor.save(provedorTraido);
                ordenNueva.setProveedor(provedorTraido);
                ordenNueva.setCardCode(provedorTraido.getCodigoProveedor());
                listaEventosOrden.add(eventoNuevo);
                ordenNueva.setEventos(listaEventosOrden);
                ordenNueva.setNotaEvento(notaEvento);
                iOrden.save(ordenNueva);
                iEvento.save(eventoNuevo);
                return eventoNuevo;
            }
        }
        else {
            log.info("ORDEN YA EXISTENTE");
            OrdenCompraModel ordenTraidaBD = oOrden.get();
            Optional<ProveedorModel> oProveedor = iProveedor.findByCodigoProveedor(ordenTraidaBD.getCardCode());
            ProveedorModel proveedorBD = oProveedor.get();
            List<EventoModel> listaEventosOrden = iEvento.findByOrden_IdOrdenCompra(ordenTraidaBD.getIdOrdenCompra());
            if (listaEventosOrden.size() != 0) {
                log.info("LA LISTA CONTIENE: {}", listaEventosOrden.size());
                int contador = listaEventosOrden.size();
                EventoModel eventoNuevo = new EventoModel();
                eventoNuevo.setNumEvento(contador + 1);
                eventoNuevo.setOrden(ordenTraidaBD);
                eventoNuevo.setFechaIngreso(LocalDate.now());
                listaEventosOrden.add(eventoNuevo);
                iEvento.save(eventoNuevo);
                ordenTraidaBD.setEventos(listaEventosOrden);
                ordenTraidaBD.setProveedor(proveedorBD);
                ordenTraidaBD.setNotaEvento(notaEvento);
                iOrden.save(ordenTraidaBD);
                return eventoNuevo;
            }
        }
        return null;
    }


    @Override
    public Optional<CalificacionReciboModel> findByEventoRecibo(Long idEvento) {
        return iRecibo.findByEvento_IdEvento(idEvento);
    }

    @Override
    public Optional<CalificacionCalidadModel> findByEventoCalidad(Long idEvento) {
        return iCalidad.findByEvento_IdEvento(idEvento);
    }

    @Override
    public Optional<CalificacionComprasModel> findByEventoCompras(Long idEvento) {
        return iCompras.findByEvento_IdEvento(idEvento);
    }




}
