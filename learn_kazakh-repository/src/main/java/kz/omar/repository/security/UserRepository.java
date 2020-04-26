package kz.omar.repository.security;

import kz.omar.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-25
 * @project learn_kazakh
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    @Query("select u from User u where u.username = :username")
    User findByUsername(@Param("username") String username);
    
}
