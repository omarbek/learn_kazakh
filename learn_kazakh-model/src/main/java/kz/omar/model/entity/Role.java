package kz.omar.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-26
 * @project learn_kazakh
 */
@Entity
@Table(name = "ROLES")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer roleId;
    
    @Column(name = "name")
    @NotNull(message = "Name must not be null")
    private String name;
    
    public Integer getRoleId() {
        return roleId;
    }
    
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
}
