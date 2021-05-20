package co.com.poli.sidemuc.models.services.habilidad;

import co.com.poli.sidemuc.models.entities.Habilidad;
import co.com.poli.sidemuc.models.repositories.HabilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HabilidadServiceImpl implements HabilidadService{

    @Autowired
    private HabilidadRepository habilidadRepository;

    @Override
    public List<Habilidad> findAll() {
        return habilidadRepository.findAll();
    }

    @Override
    public List<Habilidad> findAllByEnabled(Boolean enabled) {
        return habilidadRepository.findHabilidadByEnabled(enabled);
    }

    @Override
    public Habilidad findById(Long id) {
        return habilidadRepository.findById(id).orElse(null);
    }

    @Override
    public Habilidad save(Habilidad habilidad) {
        return habilidadRepository.save(habilidad);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Habilidad> findHabilidadByNombre(String termino) {
        return habilidadRepository.findHabilidadByNombreHabilidadContainingIgnoreCaseAndEnabledIsTrue(termino);
    }
}

