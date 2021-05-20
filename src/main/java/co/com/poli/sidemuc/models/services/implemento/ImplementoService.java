package co.com.poli.sidemuc.models.services.implemento;

import co.com.poli.sidemuc.models.entities.Implemento;

import java.util.List;

public interface ImplementoService {

    public List<Implemento> findAll();

    public List<Implemento> findAllByEnabled(Boolean enabled);

    public Implemento findById(Long id);

    public Implemento save(Implemento implemento);

    /*Metodo autocomplete*/
    public List<Implemento> findImplementoByNombre(String termino);
}
