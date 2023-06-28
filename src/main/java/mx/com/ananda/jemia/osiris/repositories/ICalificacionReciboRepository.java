package mx.com.ananda.jemia.osiris.repositories;

import mx.com.ananda.jemia.osiris.model.entity.CalificacionReciboModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICalificacionReciboRepository extends JpaRepository<CalificacionReciboModel,Long> {
    Optional<CalificacionReciboModel> findByEvento_IdEvento(Long idEvento);
}
