package kz.omar.service.test;

import kz.omar.model.entity.Test;
import kz.omar.model.entity.TestResult;
import kz.omar.model.entity.TestResultIdentity;
import kz.omar.model.entity.User;
import kz.omar.repository.test.TestRepository;
import kz.omar.repository.test.TestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Omarbek.Dinassil
 * on 2020-07-02
 * @project learn_kazakh
 */
@Service
public class TestServiceImpl implements TestService {
    
    @Autowired
    private TestResultRepository testResultRepository;
    
    @Autowired
    private TestRepository testRepository;
    
    @Override
    public void save(User user, Integer testId, int score) {
        TestResultIdentity testResultIdentity = new TestResultIdentity();
        testResultIdentity.setUser(user);
        
        Test test = testRepository.findOne(testId);
        testResultIdentity.setTest(test);
        
        TestResult testResult = new TestResult();
        testResult.setTestResultIdentity(testResultIdentity);
        testResult.setScore(score);
        
        testResultRepository.save(testResult);
    }
    
}
