package mx.com.ananda.jemia.osiris.repositories;

import mx.com.ananda.jemia.osiris.model.entity.CalificacionCalidadModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICalificacionCalidadRepository extends JpaRepository<CalificacionCalidadModel,Long> {
    Optional<CalificacionCalidadModel> findByEvento_IdEvento(Long idEvento);
}
