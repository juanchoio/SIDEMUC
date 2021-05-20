package co.com.poli.sidemuc.models.services.monitor;

import co.com.poli.sidemuc.models.entities.Monitor;
import co.com.poli.sidemuc.models.repositories.MonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitorServiceImpl implements MonitorService{

    @Autowired
    private MonitorRepository monitorRepository;

    @Override
    public List<Monitor> findAll() {
        return monitorRepository.findAll();
    }

    @Override
    public List<Monitor> findAllByEnabled(Boolean enabled) {
        return monitorRepository.findMonitorByEnabled(enabled);
    }

    @Override
    public Page<Monitor> findAllByEnabled(Pageable pageable) {
        return monitorRepository.findMonitorByEnabledIsTrue(pageable);
    }

    @Override
    public Monitor findById(Long id) {
        return monitorRepository.findById(id).orElse(null);
    }

    @Override
    public Monitor save(Monitor monitor) {
        return monitorRepository.save(monitor);
    }
}
