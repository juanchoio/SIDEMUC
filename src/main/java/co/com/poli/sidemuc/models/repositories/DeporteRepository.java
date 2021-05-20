package co.com.poli.sidemuc.models.repositories;

import co.com.poli.sidemuc.models.entities.Deporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeporteRepository extends JpaRepository<Deporte, Long> {

    public List<Deporte> findDeporteByEnabled(Boolean enabled);
}
