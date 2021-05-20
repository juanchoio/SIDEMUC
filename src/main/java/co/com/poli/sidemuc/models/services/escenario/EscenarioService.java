package co.com.poli.sidemuc.models.services.escenario;

import co.com.poli.sidemuc.models.entities.Escenario;

import java.util.List;

public interface EscenarioService {

    public List<Escenario> findAll();

    public List<Escenario> findAllByEnabled(Boolean enabled);

    public Escenario findById(Long id);

    public Escenario save(Escenario escenario);
}
