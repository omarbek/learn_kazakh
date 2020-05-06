package kz.omar.model.entity;

import kz.omar.utils.NotNullUtils;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Omarbek.Dinassil
 * on 2020-05-04
 * @project learn_kazakh
 */
@Entity
@Table(name = "ALPHABET")
@Data
public class Alphabet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "letter")
    @NotNull(message = NotNullUtils.Constants.NAME_MUST_NOT_BE_NULL_VALUE)
    private String letter;
    
    @Override
    public String toString() {
        return letter;
    }
    
}
