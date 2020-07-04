package kz.omar.model.entity;

import kz.omar.utils.NotNullUtils;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Omarbek.Dinassil
 * on 2020-07-02
 * @project learn_kazakh
 */
@Entity
@Table(name = "TEST_RESULTS")
@Data
public class TestResult {
    
    @EmbeddedId
    private TestResultIdentity testResultIdentity;
    
    @Column(name = "score")
    private int score;
    
}
