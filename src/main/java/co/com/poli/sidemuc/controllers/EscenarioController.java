package co.com.poli.sidemuc.controllers;

import co.com.poli.sidemuc.models.entities.Escenario;
import co.com.poli.sidemuc.models.services.escenario.EscenarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/escenarios")
public class EscenarioController {

    private final EscenarioService escenarioService;

    public EscenarioController(EscenarioService escenarioService) {
        this.escenarioService = escenarioService;
    }

    /*Muestra todos los escenarios habilitados*/
    @GetMapping
    public List<Escenario> findEnabled(){
        return escenarioService.findAllByEnabled(true);
    }

    @GetMapping("/detalle/{id}")
    public Escenario findById(@PathVariable Long id){
        return escenarioService.findById(id);
    }

    @GetMapping("/list-all")
    public List<Escenario> findAll(){
        return escenarioService.findAll();
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Escenario create(@RequestBody Escenario escenario){
        return escenarioService.save(escenario);
    }

    @PutMapping("/actualizar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Escenario update(@RequestBody Escenario escenario, @PathVariable Long id){
        Escenario e = escenarioService.findById(id);

        e.setNombreEscenario(escenario.getNombreEscenario());
        e.setBarrio(escenario.getBarrio());
        e.setObservaciones(escenario.getObservaciones());
        e.setEnabled(escenario.getEnabled());
        e.setFechaMantenimiento(escenario.getFechaMantenimiento());

        return escenarioService.save(e);
    }


    /*Validas si debo enviarle el Objeto Escenario
    * en el RequiestBody*/
    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Escenario delete(@PathVariable Long id){

        Escenario e = escenarioService.findById(id);
        e.setEnabled(false);
        return escenarioService.save(e);
    }

}
