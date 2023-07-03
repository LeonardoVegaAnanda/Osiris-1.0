package mx.com.ananda.jemia.osiris.service.implementation;

import mx.com.ananda.jemia.osiris.model.entity.CalificacionReciboModel;
import mx.com.ananda.jemia.osiris.model.entity.EventoModel;
import mx.com.ananda.jemia.osiris.repositories.ICalificacionReciboRepository;
import mx.com.ananda.jemia.osiris.repositories.IEventoRepository;
import mx.com.ananda.jemia.osiris.service.interfaces.ICalificacionReciboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CalificacionReciboServiceImpl implements ICalificacionReciboService {

    @Autowired
    private ICalificacionReciboRepository iRecibo;

    @Autowired
    private IEventoRepository iEvento;

    @Override
    public Optional<CalificacionReciboModel> findByIdCalif(Long idCalif) {
        return iRecibo.findById(idCalif);
    }

    @Override
    public CalificacionReciboModel saveCalif(CalificacionReciboModel calificacionRecibo, long idEvento) {
        Optional<EventoModel> oEvento = iEvento.findById(idEvento);
        EventoModel eventoTraido = oEvento.get();
        double calificacion = 0;
        calificacion= calificacionRecibo.getDocumentoCalif()+calificacionRecibo.getDocumentoFacturaCalif()+calificacionRecibo.getCantidadCalif();
        calificacionRecibo.setTotalCalif(calificacion);
        eventoTraido.setCalificacionEvento(eventoTraido.getCalificacionEvento()+calificacion);
        calificacionRecibo.setEvento(eventoTraido);
        return iRecibo.save(calificacionRecibo);
    }

    @Override
    public void updateCalif(CalificacionReciboModel calificacionRecibo) {
        iRecibo.save(calificacionRecibo);
    }
}
