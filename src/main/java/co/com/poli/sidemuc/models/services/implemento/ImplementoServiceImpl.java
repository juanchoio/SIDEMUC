package co.com.poli.sidemuc.models.services.implemento;

import co.com.poli.sidemuc.models.entities.Implemento;
import co.com.poli.sidemuc.models.repositories.ImplementoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplementoServiceImpl implements ImplementoService{

    @Autowired
    private ImplementoRepository implementoRepository;

    @Override
    public List<Implemento> findAll() {
        return implementoRepository.findAll();
    }

    @Override
    public List<Implemento> findAllByEnabled(Boolean enabled) {
        return implementoRepository.findImplementoByEnabled(enabled);
    }

    @Override
    public Implemento findById(Long id) {
        return implementoRepository.findById(id).orElse(null);
    }

    @Override
    public Implemento save(Implemento implemento) {
        return implementoRepository.save(implemento);
    }

    @Override
    public List<Implemento> findImplementoByNombre(String termino) {
        return implementoRepository.findImplementoByNombreImplementoContainingIgnoreCaseAndEnabledIsTrue(termino);
    }
}
