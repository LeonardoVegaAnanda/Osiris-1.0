package mx.com.ananda.jemia.osiris.service.implementation;

import mx.com.ananda.jemia.osiris.model.entity.CalificacionReciboModel;
import mx.com.ananda.jemia.osiris.repositories.ICalificacionReciboRepository;
import mx.com.ananda.jemia.osiris.service.interfaces.ICalificacionReciboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CalificacionReciboServiceImpl implements ICalificacionReciboService {

    @Autowired
    private ICalificacionReciboRepository iRecibo;

    @Override
    public Optional<CalificacionReciboModel> findByIdCalif(Long idCalif) {
        return iRecibo.findById(idCalif);
    }

    @Override
    public CalificacionReciboModel saveCalif(CalificacionReciboModel calificacionRecibo) {
        return iRecibo.save(calificacionRecibo);
    }

    @Override
    public void updateCalif(CalificacionReciboModel calificacionRecibo) {
        iRecibo.save(calificacionRecibo);
    }
}
