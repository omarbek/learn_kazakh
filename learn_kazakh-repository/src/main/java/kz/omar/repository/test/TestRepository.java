package kz.omar.repository.test;

import kz.omar.model.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Omarbek.Dinassil
 * on 2020-07-04
 * @project learn_kazakh
 */
public interface TestRepository extends JpaRepository<Test, Integer> {
}
