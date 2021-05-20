package co.com.poli.sidemuc.controllers;

import co.com.poli.sidemuc.models.entities.Deporte;
import co.com.poli.sidemuc.models.services.deporte.DeporteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/deportes")
public class DeporteController {

    private final DeporteService deporteService;

    public DeporteController(DeporteService deporteService) {
        this.deporteService = deporteService;
    }

    @GetMapping
    public List<Deporte> findEnabled(){
        return  deporteService.findAllByEnabled(true);
    }

    @GetMapping("/detalle/{id}")
    public Deporte findById(@PathVariable Long id){
        return deporteService.findById(id);
    }

    @GetMapping("/list-all")
    public List<Deporte> findAll(){
        return deporteService.findAll();
    }

    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Deporte create(@RequestBody Deporte deporte){
        return deporteService.save(deporte);
    }

    @PutMapping("/actualizar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Deporte update(@RequestBody Deporte deporte, @PathVariable Long id){

        Deporte d = deporteService.findById(id);

        d.setNombreDeporte(deporte.getNombreDeporte());
        d.setModalidadDeporte(deporte.getModalidadDeporte());
        d.setEnabled(deporte.getEnabled());

        return deporteService.save(d);
    }

    @DeleteMapping("/eliminar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Deporte delete(@PathVariable Long id){
        Deporte d = deporteService.findById(id);
        d.setEnabled(false);
        return deporteService.save(d);
    }

}
