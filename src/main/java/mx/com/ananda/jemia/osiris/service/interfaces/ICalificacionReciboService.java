package mx.com.ananda.jemia.osiris.service.interfaces;

import mx.com.ananda.jemia.osiris.model.entity.CalificacionReciboModel;
import mx.com.ananda.jemia.osiris.model.entity.OrdenCompraModel;

import java.util.Optional;

public interface ICalificacionReciboService {

    Optional<CalificacionReciboModel> findByIdCalif(Long idCalif);

    CalificacionReciboModel saveCalif(CalificacionReciboModel calificacionRecibo);

    void updateCalif(CalificacionReciboModel calificacionRecibo);

}
