package mx.com.ananda.jemia.osiris.service.implementation;

import mx.com.ananda.jemia.osiris.model.entity.CalificacionCalidadModel;
import mx.com.ananda.jemia.osiris.repositories.ICalificacionCalidadRepository;
import mx.com.ananda.jemia.osiris.service.interfaces.ICalificacionCalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CalificacionCalidadServiceImpl implements ICalificacionCalidadService {

    @Autowired
    private ICalificacionCalidadRepository iCalidad;

    @Override
    public Optional<CalificacionCalidadModel> findByIdCalif(Long idCalifCa) {
        return iCalidad.findById(idCalifCa);
    }

    @Override
    public CalificacionCalidadModel saveCalif(CalificacionCalidadModel calificacionCalidad) {
        return iCalidad.save(calificacionCalidad);
    }

    @Override
    public void updateCalif(CalificacionCalidadModel calificacionCalidad) {
        iCalidad.save(calificacionCalidad);
    }
}
