package mx.com.ananda.jemia.osiris.repositories;

import mx.com.ananda.jemia.osiris.model.entity.ProveedorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProveedorRepository extends JpaRepository<ProveedorModel, Long> {
    Optional<ProveedorModel> findByCodigoProveedor(String codigoProveedor);
}
