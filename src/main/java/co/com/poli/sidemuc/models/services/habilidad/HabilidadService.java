package co.com.poli.sidemuc.models.services.habilidad;

import co.com.poli.sidemuc.models.entities.Habilidad;

import java.util.List;

public interface HabilidadService {

    public List<Habilidad> findAll();

    public List<Habilidad> findAllByEnabled(Boolean enabled);

    public Habilidad findById(Long id);

    public Habilidad save(Habilidad habilidad);

    /*metodo para el autocomplete*/
    public List<Habilidad> findHabilidadByNombre(String termino);
}
