package co.com.poli.sidemuc.models.repositories;

import co.com.poli.sidemuc.models.entities.Escenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EscenarioRepository extends JpaRepository<Escenario, Long> {

    public List<Escenario> findEscenarioByEnabled(Boolean enabled);
}
