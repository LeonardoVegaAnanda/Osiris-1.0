package mx.com.ananda.jemia.osiris.service.implementation;

import mx.com.ananda.jemia.osiris.model.entity.EventoModel;
import mx.com.ananda.jemia.osiris.model.entity.OrdenCompraModel;
import mx.com.ananda.jemia.osiris.repositories.IEventoRepository;
import mx.com.ananda.jemia.osiris.repositories.IOrdenCompraRepository;
import mx.com.ananda.jemia.osiris.service.interfaces.IOrdenCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenServiceImpl implements IOrdenCompraService {

    @Autowired
    private IOrdenCompraRepository iOrden;

    @Autowired
    private IEventoRepository iEvento;


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
        return iOrden.findByDocNum(docNum);
    }

    @Override
    public OrdenCompraModel saveOrden(OrdenCompraModel ordenCompra) {
        return iOrden.save(ordenCompra);
    }

    @Override
    public List<EventoModel> findEventoByOrdenCompra(Long idOrdenCompra) {
        return iEvento.findByOrden_IdOrdenCompra(idOrdenCompra);
    }
}
