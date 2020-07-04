package kz.omar.repository.test;

import kz.omar.model.entity.TestResult;
import kz.omar.model.entity.TestResultIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Omarbek.Dinassil
 * on 2020-07-02
 * @project learn_kazakh
 */
@Repository
public interface TestResultRepository extends JpaRepository<TestResult, TestResultIdentity> {
}
