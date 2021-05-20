package co.com.poli.sidemuc.models.services.deporte;

import co.com.poli.sidemuc.models.entities.Deporte;

import java.util.List;

public interface DeporteService {

    public List<Deporte> findAll();

    public List<Deporte> findAllByEnabled(Boolean enabled);

    public Deporte findById(Long id);

    public Deporte save(Deporte deporte);
}
