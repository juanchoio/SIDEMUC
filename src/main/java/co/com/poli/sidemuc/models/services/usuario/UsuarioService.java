package co.com.poli.sidemuc.models.services.usuario;

import co.com.poli.sidemuc.models.entities.Monitor;
import co.com.poli.sidemuc.models.entities.Role;
import co.com.poli.sidemuc.models.entities.Usuario;
import co.com.poli.sidemuc.models.repositories.RoleRepositrory;
import co.com.poli.sidemuc.models.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    private UsuarioRepository usuarioRepository;
    private RoleRepositrory roleRepositrory;

    public UsuarioService(UsuarioRepository usuarioRepository, RoleRepositrory roleRepositrory) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepositrory = roleRepositrory;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findUsuarioByUsername(username);

        if(usuario == null){
            logger.error("Error en el login: no existe el usuario '"+ username +"' en el sistema!");
            throw new UsernameNotFoundException("Error en el login: no existe el usuario '"+ username +"' en el sistema!");
        }

        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority -> logger.info("Role: " + authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findUsuarioByUsername(String username) {
        return usuarioRepository.findUsuarioByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario save(Usuario usuario) {

        /*String pasword = usuario.getPassword();

        String passwordBcrypt = bCryptPasswordEncoder.encode(pasword);
        usuario.setPassword(passwordBcrypt);*/

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findByMonitor(Long id) {
        return usuarioRepository.findUsuarioByMonitor_Id(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> findAllRole() {
        return roleRepositrory.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Role findRoleById(Long id) {
        return roleRepositrory.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> findRoleByNombre(String term) {
        return roleRepositrory.findRoleByNombreContainingIgnoreCase(term);
    }
}
