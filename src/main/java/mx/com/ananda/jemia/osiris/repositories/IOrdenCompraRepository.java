package mx.com.ananda.jemia.osiris.repositories;

import mx.com.ananda.jemia.osiris.model.entity.OrdenCompraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOrdenCompraRepository extends JpaRepository<OrdenCompraModel,Long> {
    Optional<OrdenCompraModel> findByNotaEvento(String notaEvento);
    List<OrdenCompraModel> findByProveedor_CodigoProveedor(String codigoProveedor);
    Optional<OrdenCompraModel> findByDocNum(Long docNum);
    List<OrdenCompraModel> findByProveedor_IdProveedor(Long idProveedor);
}
