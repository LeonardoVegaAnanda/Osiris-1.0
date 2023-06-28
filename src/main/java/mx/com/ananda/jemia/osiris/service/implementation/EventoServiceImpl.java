package mx.com.ananda.jemia.osiris.service.implementation;

import mx.com.ananda.jemia.osiris.model.entity.CalificacionCalidadModel;
import mx.com.ananda.jemia.osiris.model.entity.CalificacionComprasModel;
import mx.com.ananda.jemia.osiris.model.entity.CalificacionReciboModel;
import mx.com.ananda.jemia.osiris.model.entity.EventoModel;
import mx.com.ananda.jemia.osiris.repositories.ICalificacionCalidadRepository;
import mx.com.ananda.jemia.osiris.repositories.ICalificacionComprasRepository;
import mx.com.ananda.jemia.osiris.repositories.ICalificacionReciboRepository;
import mx.com.ananda.jemia.osiris.repositories.IEventoRepository;
import mx.com.ananda.jemia.osiris.service.interfaces.ICalificacionReciboService;
import mx.com.ananda.jemia.osiris.service.interfaces.IEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoServiceImpl implements IEventoService {

    @Autowired
    private IEventoRepository iEvento;

    @Autowired
    private ICalificacionCalidadRepository iCalidad;

    @Autowired
    private ICalificacionComprasRepository iCompras;

    @Autowired
    private ICalificacionReciboRepository iRecibo;

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
    public EventoModel saveEvento(EventoModel evento) {
        return iEvento.save(evento);
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
