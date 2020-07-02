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
@Table(name = "TESTS")
@Data
public class Test {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "name")
    @NotNull(message = NotNullUtils.Constants.NAME_MUST_NOT_BE_NULL_VALUE)
    private String name;
    
    @Override
    public String toString() {
        return name;
    }
    
}
