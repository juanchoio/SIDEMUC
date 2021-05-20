package co.com.poli.sidemuc.controllers;

import co.com.poli.sidemuc.models.entities.Habilidad;
import co.com.poli.sidemuc.models.services.habilidad.HabilidadService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/habilidades")
public class HabilidadController {


    private final HabilidadService habilidadService;

    public HabilidadController(HabilidadService habilidadService) {
        this.habilidadService = habilidadService;
    }

    /*muestra todas las habilidades habilitadas*/
    @GetMapping
    public List<Habilidad> findEnabled(){
        return habilidadService.findAllByEnabled(true);
    }

    @GetMapping("/detalle/{id}")
    public Habilidad findById(@PathVariable Long id){
        return habilidadService.findById(id);
    }

    @GetMapping("/list-all")
    public List<Habilidad> findAll(){
        return habilidadService.findAll();
    }

    /**Método handler para filtrar las habilidaddes y usarlas en el autocomplete
     * Retorna las habilidades cuyo atributo enabled==true*/
    @GetMapping("/filtrar/{termino}")
    public List<Habilidad> habilidadesFilter(@PathVariable String termino){
        return habilidadService.findHabilidadByNombre(termino);
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Habilidad create(@RequestBody Habilidad habilidad){
        return habilidadService.save(habilidad);
    }

    @PutMapping("/actualizar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Habilidad update(@RequestBody Habilidad habilidad, @PathVariable Long id){
        Habilidad h = habilidadService.findById(id);

        h.setNombreHabilidad(habilidad.getNombreHabilidad());
        h.setEnabled(habilidad.getEnabled());

        return habilidadService.save(h);
    }

    /*Validar si debo enviarle el objeto Habilidad*/
    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Habilidad delete(@PathVariable Long id){
        Habilidad h = habilidadService.findById(id);

        h.setEnabled(false);

        return habilidadService.save(h);
    }

}
