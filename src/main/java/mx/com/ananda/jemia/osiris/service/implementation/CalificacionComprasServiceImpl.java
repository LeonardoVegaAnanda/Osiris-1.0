package mx.com.ananda.jemia.osiris.service.implementation;

import mx.com.ananda.jemia.osiris.model.entity.CalificacionComprasModel;
import mx.com.ananda.jemia.osiris.repositories.ICalificacionComprasRepository;
import mx.com.ananda.jemia.osiris.service.interfaces.ICalificacionComprasService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CalificacionComprasServiceImpl implements ICalificacionComprasService{

    @Autowired
    private ICalificacionComprasRepository iCompras;

    @Override
    public Optional<CalificacionComprasModel> findByIdCalif(Long idCalifCo) {
        return iCompras.findById(idCalifCo);
    }

    @Override
    public CalificacionComprasModel saveCalif(CalificacionComprasModel calificacionCompras) {
        return iCompras.save(calificacionCompras);
    }

    @Override
    public void updateCalif(CalificacionComprasModel calificacionCompras) {
        iCompras.save(calificacionCompras);
    }
}
