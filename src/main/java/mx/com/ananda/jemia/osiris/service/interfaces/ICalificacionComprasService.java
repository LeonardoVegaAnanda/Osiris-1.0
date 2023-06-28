package mx.com.ananda.jemia.osiris.service.interfaces;


import mx.com.ananda.jemia.osiris.model.entity.CalificacionComprasModel;

import java.util.Optional;

public interface ICalificacionComprasService {


    Optional<CalificacionComprasModel> findByIdCalif(Long idCalifCo);

    CalificacionComprasModel saveCalif(CalificacionComprasModel calificacionCompras);

    void updateCalif(CalificacionComprasModel calificacionCompras);
}
