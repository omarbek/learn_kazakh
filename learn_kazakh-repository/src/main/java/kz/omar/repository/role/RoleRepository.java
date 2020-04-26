package kz.omar.repository.role;

import kz.omar.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-26
 * @project learn_kazakh
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    
    @Query("select r from Role r order by r.name")
    List<Role> getRoles();
    
}
