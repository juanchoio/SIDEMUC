package co.com.poli.sidemuc.models.services.monitor;


import co.com.poli.sidemuc.models.entities.Monitor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MonitorService {

    public List<Monitor> findAll();

    public List<Monitor> findAllByEnabled(Boolean enabled);

    public Page<Monitor> findAllByEnabled(Pageable pageable);

    public Monitor findById(Long id);

    public Monitor save(Monitor monitor);
}
