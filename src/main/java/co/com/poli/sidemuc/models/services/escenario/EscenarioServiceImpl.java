package co.com.poli.sidemuc.models.services.escenario;

import co.com.poli.sidemuc.models.entities.Escenario;
import co.com.poli.sidemuc.models.repositories.EscenarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EscenarioServiceImpl implements EscenarioService{

    @Autowired
    private EscenarioRepository escenarioRepository;

    @Override
    public List<Escenario> findAll() {
        return escenarioRepository.findAll();
    }

    @Override
    public List<Escenario> findAllByEnabled(Boolean enabled) {
        return escenarioRepository.findEscenarioByEnabled(enabled);
    }

    @Override
    public Escenario findById(Long id) {
        return escenarioRepository.findById(id).orElse(null);
    }

    @Override
    public Escenario save(Escenario escenario) {
        return escenarioRepository.save(escenario);
    }
}
