package co.com.poli.sidemuc.models.services.deporte;

import co.com.poli.sidemuc.models.entities.Deporte;
import co.com.poli.sidemuc.models.repositories.DeporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeporteServiceImpl implements DeporteService{

    @Autowired
    private DeporteRepository deporteRepository;

    @Override
    public List<Deporte> findAll() {
        return deporteRepository.findAll();
    }

    @Override
    public List<Deporte> findAllByEnabled(Boolean enabled) {
        return deporteRepository.findDeporteByEnabled(enabled);
    }

    @Override
    public Deporte findById(Long id) {
        return deporteRepository.findById(id).orElse(null);
    }

    @Override
    public Deporte save(Deporte deporte) {
        return deporteRepository.save(deporte);
    }
}
