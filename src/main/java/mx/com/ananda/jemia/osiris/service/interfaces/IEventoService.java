package mx.com.ananda.jemia.osiris.service.interfaces;


import mx.com.ananda.jemia.osiris.model.entity.CalificacionCalidadModel;
import mx.com.ananda.jemia.osiris.model.entity.CalificacionComprasModel;
import mx.com.ananda.jemia.osiris.model.entity.CalificacionReciboModel;
import mx.com.ananda.jemia.osiris.model.entity.EventoModel;

import java.util.List;
import java.util.Optional;

public interface IEventoService {

    List<EventoModel> findAlleventos();

    Optional<EventoModel> findEventoModelById(Long idEvento);

    void updateEvento(EventoModel evento);

    EventoModel saveEvento(EventoModel evento);

    Optional<CalificacionReciboModel> findByEventoRecibo(Long idEvento);

    Optional<CalificacionCalidadModel> findByEventoCalidad(Long idEvento);

    Optional<CalificacionComprasModel> findByEventoCompras(Long idEvento);
}
