package co.com.poli.sidemuc.controllers;

import co.com.poli.sidemuc.models.entities.Monitor;
import co.com.poli.sidemuc.models.services.monitor.MonitorService;
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
@RequestMapping("/monitores")
public class MonitorController {

    private final MonitorService monitorService;

    private final Logger log = LoggerFactory.getLogger(MonitorController.class);

    public MonitorController(MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    /*permitido a todos en el resource server*/
    @GetMapping
    public List<Monitor> findEnabled(){
        return monitorService.findAllByEnabled(true);
    }

    /*permitido a todos en el resource server*/
    @GetMapping("/page/{page}")
    public Page<Monitor> findEnabled(@PathVariable Integer page){
        Pageable pageable = PageRequest.of(page, 4);
        return monitorService.findAllByEnabled(pageable);
    }//ok

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/detalle/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Monitor monitor = null;
        Map<String, Object> response = new HashMap<>();
        try{
            monitor = monitorService.findById(id);
        } catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la consulta en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(monitor == null){
            response.put("mensaje", "El Monitor ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Monitor>(monitor, HttpStatus.OK);
    }//ok

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/list-all")
    public List<Monitor> findAll(){
        return monitorService.findAll();
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/crear")
    public ResponseEntity<?> create(@Valid @RequestBody Monitor monitor, BindingResult result){
        Monitor monitorNew = null;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()){
            /**List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);*/
            List<String> errors = new ArrayList<>();
            for(FieldError err: result.getFieldErrors()){
                errors.add("El campo '"+ err.getField() + "' "+err.getDefaultMessage());
            }
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            monitorNew = monitorService.save(monitor);
        } catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el insert en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El Monitor ha sido creado con éxito!");
        response.put("monitor", monitorNew);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }//ok

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Monitor monitor, BindingResult result, @PathVariable Long id){
        Monitor m = monitorService.findById(id);

        Monitor mUpdated = null;

        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if(m == null){
            response.put("mensaje", "Error: no se puedo editar el Monitor ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            m.setNumeroDocumento(monitor.getNumeroDocumento());
            m.setNombrePersona(monitor.getNombrePersona());
            m.setApellidoPersona(monitor.getApellidoPersona());
            m.setFechaNacimiento(monitor.getFechaNacimiento());
            m.setEdad(monitor.getEdad());
            m.setDireccion(monitor.getDireccion());
            m.setTelefono(monitor.getTelefono());
            m.setSexo(monitor.getSexo());
            m.setCorreo(monitor.getCorreo());
            m.setEnabled(monitor.getEnabled());
            m.setDeporte(monitor.getDeporte());
            m.setEscenario(monitor.getEscenario());
            m.setImplementos(monitor.getImplementos());

            mUpdated = monitorService.save(monitor);
        } catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el insert en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El Monitor ha sido actualizado con éxito!");
        response.put("monitor", mUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }//ok

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Monitor m = monitorService.findById(id);
        Monitor mDeleted = null;

        Map<String, Object> response = new HashMap<>();

        if(m == null){
            response.put("mensaje", "Error: no se puedo editar el Monitor ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try{
            m.setEnabled(false);
            mDeleted = monitorService.save(m);
        } catch (DataAccessException e){
            response.put("mensaje", "Error al eliminar el monitor de la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El Monitor ha sido eliminado con éxito!");
        response.put("monitor", mDeleted);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }//ok

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/upload")
    public ResponseEntity<?> uploadPhoto(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
        Map<String, Object> response = new HashMap<>();

        Monitor monitor = monitorService.findById(id);

        if(!archivo.isEmpty()){
            String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
            Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
            log.info(rutaArchivo.toString());

            try{
                Files.copy(archivo.getInputStream(), rutaArchivo);
            } catch (IOException e){
                response.put("mensaje", "Error al subir el la foto del monitor " + monitor.getNombrePersona() + ".");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            /*borramos la foto anterior cuando es actualizada*/
            String nombreFotoAnterior = monitor.getFoto();
            if(nombreFotoAnterior != null && nombreFotoAnterior.length() > 0){
                Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
                File archivoFotoAnterior = rutaFotoAnterior.toFile();
                if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()){
                    archivoFotoAnterior.delete();
                }
            }

            monitor.setFoto(nombreArchivo);
            monitorService.save(monitor);

            response.put("monitor", monitor);
            response.put("mensaje", "Has dubido correctmente la foto del monitor: " + monitor.getNombrePersona() + " " + monitor.getApellidoPersona());//PONER EL NOMBRE DEL DEPORTISTA
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    /*permitido a TODOS en el resource server*/
    @Secured({"ROLE_ADMIN"})
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
