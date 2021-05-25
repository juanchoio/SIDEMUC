package co.com.poli.sidemuc.models.services.usuario;

import co.com.poli.sidemuc.models.entities.Monitor;
import co.com.poli.sidemuc.models.entities.Role;
import co.com.poli.sidemuc.models.entities.Usuario;

import java.util.List;

public interface IUsuarioService {

    public Usuario findUsuarioByUsername(String username);

    public Usuario findById(Long id);

    public Usuario save(Usuario usuario);

    public Usuario findByMonitor(Long id);

    /** devuelve todo los roles
     * se inyecta el RoleRepository en el servicio usuario*/
    public List<Role> findAllRole();

    public Role findRoleById(Long id);

    /*AutoComplete*/
    public  List<Role> findRoleByNombre(String term);


}
