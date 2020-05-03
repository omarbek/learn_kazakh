package kz.omar.model.entity;

import kz.omar.utils.NotNullUtils;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-26
 * @project learn_kazakh
 */
@Entity
@Table(name = "ROLES")
@Data
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer roleId;
    
    @Column(name = "name")
    @NotNull(message = NotNullUtils.Constants.NAME_MUST_NOT_BE_NULL_VALUE)
    private String name;
    
    @Override
    public String toString() {
        return name;
    }
    
}
