package co.com.poli.sidemuc.models.repositories;

import co.com.poli.sidemuc.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findUsuarioByUsername(String username);

    /*La misma consulta de otra forma*/
    @Query("select u from Usuario u where u.username=?1")
    public Usuario findUsuarioByUsername2(String username);

    public Usuario findUsuarioByMonitor_Id(Long id);
}
