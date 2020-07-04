package kz.omar.service.test;

import kz.omar.model.entity.Test;
import kz.omar.model.entity.User;

/**
 * @author Omarbek.Dinassil
 * on 2020-07-02
 * @project learn_kazakh
 */
public interface TestService {
    
    void save(User user, Integer testId, int score);
    
}
