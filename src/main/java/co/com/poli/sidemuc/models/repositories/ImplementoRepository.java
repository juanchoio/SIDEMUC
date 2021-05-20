package co.com.poli.sidemuc.models.repositories;

import co.com.poli.sidemuc.models.entities.Implemento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImplementoRepository extends JpaRepository<Implemento, Long> {

    public List<Implemento> findImplementoByEnabled(Boolean enabled);

    public List<Implemento> findImplementoByNombreImplementoContainingIgnoreCaseAndEnabledIsTrue(String termino);
}
