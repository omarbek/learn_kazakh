package kz.omar.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-26
 * @project learn_kazakh
 */
@Entity
@Table(name = "TASKS")
@Data
public class Task {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int taskId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Task parent;
    
    @Column(name = "name")
    @NotNull(message = "Name must not be null")
    private String name;
    
    @Column(name = "navigate_path")
    private String navigatePath;
    
    @Column(name = "icon_path")
    private String iconPath;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;
    
    @Column(name = "common", nullable = false, columnDefinition = "boolean default false")
    private boolean common;
    
    @Override
    public String toString() {
        return name;
    }
    
}
