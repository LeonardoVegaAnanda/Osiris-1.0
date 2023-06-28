package mx.com.ananda.jemia.osiris.repositories;

import mx.com.ananda.jemia.osiris.model.entity.CalificacionComprasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICalificacionComprasRepository extends JpaRepository<CalificacionComprasModel,Long> {
    Optional<CalificacionComprasModel> findByEvento_IdEvento(Long idEvento);
}
