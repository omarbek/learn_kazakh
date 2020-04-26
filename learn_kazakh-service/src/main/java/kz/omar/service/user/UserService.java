package kz.omar.service.user;

import kz.omar.model.entity.Role;
import kz.omar.model.entity.User;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-25
 * @project learn_kazakh
 */
public interface UserService {
    
    void save(String username, String password, Role role);
    
    User getUserByUsername(String username);
    
    User getCurrentUser();
    
}
