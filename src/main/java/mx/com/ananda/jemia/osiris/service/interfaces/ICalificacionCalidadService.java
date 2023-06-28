package mx.com.ananda.jemia.osiris.service.interfaces;

import mx.com.ananda.jemia.osiris.model.entity.CalificacionCalidadModel;
import mx.com.ananda.jemia.osiris.model.entity.EventoModel;
import mx.com.ananda.jemia.osiris.model.entity.OrdenCompraModel;

import java.util.Optional;

public interface ICalificacionCalidadService {

    Optional<CalificacionCalidadModel> findByIdCalif(Long idCalifCa);

    CalificacionCalidadModel saveCalif(CalificacionCalidadModel calificacionCalidad);

    void updateCalif(CalificacionCalidadModel calificacionCalidad);

}
