package co.com.poli.sidemuc.models.services.categoria;

import co.com.poli.sidemuc.models.entities.Categoria;
import co.com.poli.sidemuc.models.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService{

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public List<Categoria> findAllByEnabled(Boolean enabled) {
        /*enviamos enabled desde el controlador para poder establecer
        * diferentes valores para la variable*/
        return categoriaRepository.findCategoriaByEnabled(enabled);
    }

    @Override
    public Categoria findById(Long id) {
        /*si no encuentra la categoria por id retorna null*/
        return categoriaRepository.findById(id).orElse(null);
    }

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
}
