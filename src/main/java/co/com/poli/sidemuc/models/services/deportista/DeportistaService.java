package co.com.poli.sidemuc.models.services.deportista;

import co.com.poli.sidemuc.models.entities.Deportista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DeportistaService {

    public List<Deportista> findAll();

    public List<Deportista> findAllByEnabled(Boolean enabled);

    public Page<Deportista> findAllByEnabled(Pageable pageable);

    public Deportista findById(Long id);

    public Deportista save(Deportista deportista);
}
