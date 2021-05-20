package co.com.poli.sidemuc.models.services.categoria;

import co.com.poli.sidemuc.models.entities.Categoria;

import java.util.List;

public interface CategoriaService {

    public List<Categoria> findAll();

    public List<Categoria> findAllByEnabled(Boolean enabled);

    public Categoria findById(Long id);

    public Categoria save(Categoria categoria);

    /*para eliminar, enviamos el Objeto Categoria
    * y actualizamos el valor de su atributo
    * enabled = false*/
}
