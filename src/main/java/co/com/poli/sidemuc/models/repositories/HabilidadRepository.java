package co.com.poli.sidemuc.models.repositories;

import co.com.poli.sidemuc.models.entities.Habilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabilidadRepository extends JpaRepository<Habilidad, Long> {

    public List<Habilidad> findHabilidadByEnabled(Boolean enabled);

    /**Método que filtra las habilidades*/
    @Query("select h from Habilidad h where h.nombreHabilidad like %?1%")
    public List<Habilidad> findHabilidadByNombreHabilidad(String termino);

    public List<Habilidad> findHabilidadByNombreHabilidadContainingIgnoreCaseAndEnabledIsTrue(String termino);
}
