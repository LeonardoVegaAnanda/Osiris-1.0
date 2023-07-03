package mx.com.ananda.jemia.osiris.service.implementation;

import mx.com.ananda.jemia.osiris.model.entity.CalificacionComprasModel;
import mx.com.ananda.jemia.osiris.model.entity.EventoModel;
import mx.com.ananda.jemia.osiris.repositories.ICalificacionComprasRepository;
import mx.com.ananda.jemia.osiris.repositories.IEventoRepository;
import mx.com.ananda.jemia.osiris.service.interfaces.ICalificacionComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CalificacionComprasServiceImpl implements ICalificacionComprasService{

    @Autowired
    private ICalificacionComprasRepository iCompras;

    @Autowired
    private IEventoRepository iEvento;

    @Override
    public Optional<CalificacionComprasModel> findByIdCalif(Long idCalifCo) {
        return iCompras.findById(idCalifCo);
    }

    @Override
    public CalificacionComprasModel saveCalif(CalificacionComprasModel calificacionCompras, long idEvento) {
        Optional<EventoModel> oEvento = iEvento.findById(idEvento);
        EventoModel eventoTraido = oEvento.get();
        double calificacion = 0;
        calificacion = calificacionCompras.getServicioCalif() + calificacionCompras.getTiemposCalif();
        calificacionCompras.setTotalCalif(calificacion);
        eventoTraido.setCalificacionEvento(eventoTraido.getCalificacionEvento()+calificacion);
        calificacionCompras.setEvento(eventoTraido);
        return iCompras.save(calificacionCompras);
    }

    @Override
    public void updateCalif(CalificacionComprasModel calificacionCompras) {
        iCompras.save(calificacionCompras);
    }
}
