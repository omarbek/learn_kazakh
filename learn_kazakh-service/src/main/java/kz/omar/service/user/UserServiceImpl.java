package kz.omar.service.user;

import kz.omar.model.entity.Role;
import kz.omar.model.entity.User;
import kz.omar.repository.user.UserRepository;
import kz.omar.service.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * @author Omarbek.Dinassil
 * on 2020-04-25
 * @project learn_kazakh
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    public void save(String username, String password, Role role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        userRepository.save(user);
    }
    
    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    @Override
    public User getCurrentUser() {
        String username = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal()).getUsername();
        return getUserByUsername(username);
    }
    
}
