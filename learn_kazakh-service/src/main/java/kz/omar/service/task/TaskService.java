package kz.omar.service.task;

import kz.omar.model.entity.Task;

import java.util.List;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-26
 * @project learn_kazakh
 */
public interface TaskService {
    
    List<Task> getTasksWithNoParentByRoleId(Integer roleId);
    
    List<Task> getTasksByParentId(Integer menuId, Integer id);
    
    Integer taskNameBelongForThisUser(String taskName, Integer roleId);
}
