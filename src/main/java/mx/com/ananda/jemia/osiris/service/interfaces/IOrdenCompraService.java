package mx.com.ananda.jemia.osiris.service.interfaces;

import mx.com.ananda.jemia.osiris.model.entity.EventoModel;
import mx.com.ananda.jemia.osiris.model.entity.OrdenCompraModel;
import org.aspectj.weaver.ast.Or;

import java.util.List;
import java.util.Optional;

public interface IOrdenCompraService {

    List<OrdenCompraModel> findAllOrdenes();
    Optional<OrdenCompraModel> findOrdenById(Long idOrden);
    Optional<OrdenCompraModel> findOrdenByDocNum(Long docNum);
    OrdenCompraModel saveOrden(OrdenCompraModel ordenCompra);
    List<EventoModel> findEventoByOrdenCompra(Long idOrdenCompra);

    void asignarCalificacionOrden(Long idOrdenCompra);
    public Optional<OrdenCompraModel> asignarOrden(Long idOrden, Long numOrden);

}
