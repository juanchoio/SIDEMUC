package co.com.poli.sidemuc.controllers;

import co.com.poli.sidemuc.models.entities.Deportista;
import co.com.poli.sidemuc.models.services.deportista.DeportistaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/deportistas")
public class DeportistaController {

    private final DeportistaService deportistaService;

    private final Logger log = LoggerFactory.getLogger(DeportistaController.class);

    public DeportistaController(DeportistaService deportistaService) {
        this.deportistaService = deportistaService;
    }

    /*permitido a todos en el resource server*/
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping
    public List<Deportista> findEnabled(){
        return deportistaService.findAllByEnabled(true);
    }

    /*permitido a todos en el resource server*/
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/page/{page}")
    public Page<Deportista> findEnabled(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 4);
        return deportistaService.findAllByEnabled(pageable);
    }//ok

    /*permitido a rol usuario y administrador en el resource server*/
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/detalle/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Deportista deportista = null;
        Map<String, Object> response = new HashMap<>();
        try{
             deportista = deportistaService.findById(id);
        } catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la consulta en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(deportista == null){
            response.put("mensaje", "El Deportista ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Deportista>(deportista, HttpStatus.OK);
    }//ok

    /*permitido a rol usuario y administrador en el resource server*/
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/list-all")
    public List<Deportista> findAll(){
        return deportistaService.findAll();
    }

    /*permitido a rol usuario y administrador en el resource server*/
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/crear")
    public ResponseEntity<?> create(@Valid @RequestBody Deportista deportista, BindingResult result){

        Deportista deportistaNew = null;
        Map<String, Object> response = new HashMap<>();

        /*verificamos si vienen errores desde el form
        * Validando los errores*/
        if(result.hasErrors()){
            List<String> errors = new ArrayList<>();
            for(FieldError err: result.getFieldErrors()){
                errors.add("El campo '"+ err.getField() + "' "+err.getDefaultMessage());
            }
            //de otra forma manejando stream
            /*List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '"+ err.getField() + "' "+err.getDefaultMessage())
                    .collect(Collectors.toList());*/
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try{
            deportistaNew = deportistaService.save(deportista);
        } catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el insert en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El Deportista ha sido creado con éxito!");
        response.put("deportista", deportistaNew);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }//ok

    /*permitido a rol usuario y administrador en el resource server*/
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Deportista deportista, BindingResult result, @PathVariable Long id){
        Deportista dep = deportistaService.findById(id);

        Deportista depUpdated = null;

        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()){//validamos si vienen errores
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '"+ err.getField() + "' "+err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if(dep == null){
            response.put("mensaje", "Error: no se puedo editar el Deportista ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            dep.setNumeroDocumento(deportista.getNumeroDocumento());
            dep.setNombrePersona(deportista.getNombrePersona());
            dep.setApellidoPersona(deportista.getApellidoPersona());
            dep.setFechaNacimiento(deportista.getFechaNacimiento());
            dep.setEdad(deportista.getEdad());
            dep.setDireccion(deportista.getDireccion());
            dep.setTelefono(deportista.getTelefono());
            dep.setSexo(deportista.getSexo());
            dep.setCorreo(deportista.getCorreo());
            dep.setEnabled(deportista.getEnabled());
            dep.setDeporte(deportista.getDeporte());
            dep.setCategoria(deportista.getCategoria());
            dep.setHabilidades(deportista.getHabilidades());

            depUpdated = deportistaService.save(dep);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el insert en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El Deportista ha sido actualizado con éxito!");
        response.put("deportista", depUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }//ok

    /*permitido a rol  administrador en el resource server*/
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        Deportista d = deportistaService.findById(id);
        Deportista dDeleted = null;

        Map<String, Object> response = new HashMap<>();

        if(d == null){
            response.put("mensaje", "Error: no se puedo editar el Deportista ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try{
            d.setEnabled(false);
            dDeleted = deportistaService.save(d);
        } catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar el deportista de la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El Deportista ha sido eliminado con éxito!");
        response.put("deportista", dDeleted);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }//ok

    /*permitido a rol usuario y administrador en el resource server*/
    //@Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/upload")
    public ResponseEntity<?> uploadPhoto(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
        Map<String, Object> response = new HashMap<>();

        Deportista deportista = deportistaService.findById(id);

        if(!archivo.isEmpty()){
            String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
            Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
            log.info(rutaArchivo.toString());

            try{
                Files.copy(archivo.getInputStream(), rutaArchivo);
            } catch (IOException e){
                response.put("mensaje", "Error al subir el la foto del deportista" + deportista.getNombrePersona() + ".");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            /*borramos la foto anterior cuando es actualizada*/
            String nombreFotoAnterior = deportista.getFoto();
            if(nombreFotoAnterior != null && nombreFotoAnterior.length() > 0){
                Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
                File archivoFotoAnterior = rutaFotoAnterior.toFile();
                if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()){
                    archivoFotoAnterior.delete();
                }
            }

            deportista.setFoto(nombreArchivo);
            deportistaService.save(deportista);

            response.put("deportista", deportista);
            response.put("mensaje", "Has dubido correctmente la foto del deportista: " + deportista.getNombrePersona() + " " + deportista.getApellidoPersona());//PONER EL NOMBRE DEL DEPORTISTA

        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    /*permitido a TODOS en el resource server*/
    //@Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/uploads/img/{nombreFoto:.+}")//el parametro va a tener una extension
    public ResponseEntity<Resource> viewPhoto(@PathVariable String nombreFoto){

        Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
        log.info(rutaArchivo.toString());
        Resource recurso = null;

        try {
            recurso = new UrlResource(rutaArchivo.toUri());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }

        if(!recurso.exists() && !recurso.isReadable()){
            rutaArchivo = Paths.get("src/main/resources/static/images").resolve("no_user.png").toAbsolutePath();
            try {
                recurso = new UrlResource(rutaArchivo.toUri());
            } catch (MalformedURLException e){
                e.printStackTrace();
            }
            log.error("No se pudo cargar la imagen: " + nombreFoto);
        }

        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }

}
