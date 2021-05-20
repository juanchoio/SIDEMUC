package co.com.poli.sidemuc.controllers;

import co.com.poli.sidemuc.models.entities.Implemento;
import co.com.poli.sidemuc.models.services.implemento.ImplementoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/implementos")
public class ImplementoController {

    private final ImplementoService implementoService;

    public ImplementoController(ImplementoService implementoService) {
        this.implementoService = implementoService;
    }

    /*Muestra todos los implementos habilitados*/
    @GetMapping
    public List<Implemento> findEnabled(){
        return implementoService.findAllByEnabled(true);
    }

    @GetMapping("/detalle/{id}")
    public Implemento findById(@PathVariable Long id){
        return implementoService.findById(id);
    }

    @GetMapping("/list-all")
    public List<Implemento> findAll(){
        return implementoService.findAll();
    }

    /**Método handler para filtrar las habilidaddes y usarlas en el autocomplete
     * Retorna las habilidades cuyo atributo enabled==true*/
    @GetMapping("/filtrar/{termino}")
    public  List<Implemento> implementosFilter(@PathVariable String termino){
        return implementoService.findImplementoByNombre(termino);
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Implemento create(@RequestBody Implemento implemento){
        return implementoService.save(implemento);
    }

    @PutMapping("/actualizar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Implemento update(@RequestBody Implemento implemento, @PathVariable Long id){
        Implemento i = implementoService.findById(id);

        i.setNombreImplemento(implemento.getNombreImplemento());
        i.setCantidad(implemento.getCantidad());
        i.setEnabled(implemento.getEnabled());

        return implementoService.save(i);
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Implemento delete(@PathVariable Long id){

        Implemento i = implementoService.findById(id);

        i.setEnabled(false);

        return implementoService.save(i);
    }

}
