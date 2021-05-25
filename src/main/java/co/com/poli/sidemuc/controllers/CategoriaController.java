package co.com.poli.sidemuc.controllers;

import co.com.poli.sidemuc.models.entities.Categoria;
import co.com.poli.sidemuc.models.services.categoria.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/categorias")
public class CategoriaController {


    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    /*Muestra todas las categorias habilitadas*/
    /*permitido a todos en el resource server*/
    @GetMapping
    public List<Categoria> findEnabled(){
        return categoriaService.findAllByEnabled(true);
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/detalle/{id}")
    public Categoria findById(@PathVariable Long id){
        return categoriaService.findById(id);
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/list-all")
    public List<Categoria> findAll(){
        return categoriaService.findAll();
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria create(@RequestBody Categoria categoria){
        return categoriaService.save(categoria);
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/actualizar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria update(@RequestBody Categoria categoria, @PathVariable Long id){
        Categoria c = categoriaService.findById(id);

        c.setNombreCategoria(categoria.getNombreCategoria());
        c.setRangoEdad(categoria.getRangoEdad());
        c.setEnabled(categoria.getEnabled());

        return categoriaService.save(c);
    }


    /*REVISAR
    * verificar si es necesario enviar el objeto
    * o solo con el path variable se puede hacer la operacion*/
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Categoria delete(@PathVariable Long id){
        Categoria c = categoriaService.findById(id);

        c.setEnabled(false);

        return categoriaService.save(c);
    }


}
