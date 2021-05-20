package co.com.poli.sidemuc.models.repositories;

import co.com.poli.sidemuc.models.entities.Monitor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long> {

    public List<Monitor> findMonitorByEnabled(Boolean enabled);

    public Page<Monitor> findMonitorByEnabledIsTrue(Pageable pageable);
}
