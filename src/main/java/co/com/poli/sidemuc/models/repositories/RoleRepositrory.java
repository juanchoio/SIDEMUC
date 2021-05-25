package co.com.poli.sidemuc.models.repositories;

import co.com.poli.sidemuc.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepositrory extends JpaRepository<Role, Long> {

    public List<Role> findRoleByNombreContainingIgnoreCase(String term);
}
