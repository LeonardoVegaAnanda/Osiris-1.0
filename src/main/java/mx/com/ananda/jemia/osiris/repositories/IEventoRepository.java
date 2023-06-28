package mx.com.ananda.jemia.osiris.repositories;

import mx.com.ananda.jemia.osiris.model.entity.EventoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEventoRepository extends JpaRepository<EventoModel, Long> {
    List<EventoModel> findByOrden_IdOrdenCompra(Long idOrdenCompra);
}
