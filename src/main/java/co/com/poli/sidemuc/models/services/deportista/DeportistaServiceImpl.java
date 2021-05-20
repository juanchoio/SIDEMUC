package co.com.poli.sidemuc.models.services.deportista;

import co.com.poli.sidemuc.models.entities.Deportista;
import co.com.poli.sidemuc.models.repositories.DeportistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeportistaServiceImpl implements DeportistaService{

    @Autowired
    private DeportistaRepository deportistaRepository;

    @Override
    public List<Deportista> findAll() {
        return deportistaRepository.findAll();
    }

    @Override
    public List<Deportista> findAllByEnabled(Boolean enabled) {
        return deportistaRepository.findDeportistaByEnabled(enabled);
    }

    @Override
    public Page<Deportista> findAllByEnabled(Pageable pageable) {
        return deportistaRepository.findDeportistaByEnabledIsTrue(pageable);
    }

    @Override
    public Deportista findById(Long id) {
        return deportistaRepository.findById(id).orElse(null);
    }

    @Override
    public Deportista save(Deportista deportista) {
        return deportistaRepository.save(deportista);
    }
}
