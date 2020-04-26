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
@Table(name = "TASKS")
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
    
    @ManyToMany
    @JoinTable(name = "role_tasks",
            joinColumns = @JoinColumn(name = "taskId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private List<Role> roles = new ArrayList<>();
    
    public int getTaskId() {
        return taskId;
    }
    
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
    
    public Task getParent() {
        return parent;
    }
    
    public void setParent(Task parent) {
        this.parent = parent;
    }
    
    public String getNavigatePath() {
        return navigatePath;
    }
    
    public void setNavigatePath(String navigatePath) {
        this.navigatePath = navigatePath;
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
    
    public List<Role> getRoles() {
        return roles;
    }
    
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
    public void assignRoleToTask(Role role) {
        this.roles.add(role);
    }
    
}
