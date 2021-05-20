package co.com.poli.sidemuc.models.repositories;

import co.com.poli.sidemuc.models.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    /*pendiente agregar el query si no funciona
    * @Query("select c from Categoria c where c.enabled like %?1%")*/
    public List<Categoria> findCategoriaByEnabled(Boolean enabled);
}
