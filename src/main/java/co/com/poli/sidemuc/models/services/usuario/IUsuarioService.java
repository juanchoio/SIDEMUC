package co.com.poli.sidemuc.models.services.usuario;

import co.com.poli.sidemuc.models.entities.Usuario;

public interface IUsuarioService {

    public Usuario findUsuarioByUsername(String username);

}
