package co.com.poli.sidemuc.models.repositories;

import co.com.poli.sidemuc.models.entities.Deportista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeportistaRepository extends JpaRepository<Deportista, Long> {

    public List<Deportista> findDeportistaByEnabled(Boolean enabled);

    public Page<Deportista> findDeportistaByEnabledIsTrue(Pageable pageable);
}
