package co.com.poli.sidemuc.controllers;

import co.com.poli.sidemuc.models.entities.Role;
import co.com.poli.sidemuc.models.entities.Usuario;
import co.com.poli.sidemuc.models.services.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/detalle/{id}")
    public Usuario findById(@PathVariable Long id){
        return usuarioService.findById(id);
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/detalle-usuario-monitor/{id}")
    public Usuario findByMonitor(@PathVariable Long id){
        return usuarioService.findByMonitor(id);
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario create(@RequestBody Usuario usuario){

        String password = usuario.getPassword();
        String passwordBcrypt = bCryptPasswordEncoder.encode(password);

        usuario.setPassword(passwordBcrypt);
        return usuarioService.save(usuario);
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/actualizar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario update(@RequestBody Usuario usuario, @PathVariable Long id){
        Usuario u = usuarioService.findById(id);

        u.setMonitor(usuario.getMonitor());
        u.setUsername(usuario.getUsername());
        u.setPassword(usuario.getPassword());
        u.setRoles(usuario.getRoles());

        return usuarioService.save(u);
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/roles")
    public List<Role> findAllRole(){
        return usuarioService.findAllRole();
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/role/{id}")
    public Role findRoleById(@PathVariable Long id){
        return usuarioService.findRoleById(id);
    }

    /**Metodo para filtrar los roles en el autocomplete*/
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/filtrar/{termino}")
    public List<Role> roleFilter(@PathVariable String termino){
        return usuarioService.findRoleByNombre(termino);
    }

}
