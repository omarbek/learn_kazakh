package kz.omar.service.task;

import kz.omar.model.entity.Task;
import kz.omar.repository.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-26
 * @project learn_kazakh
 */
@Service
public class TaskServiceImpl implements TaskService {
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Override
    public List<Task> getTasksWithNoParentByRoleId(Integer roleId) {
        if (roleId == null) {
            return taskRepository.getCommonTasks();
        }
        return taskRepository.getTasksWithNoParentByRoleId(roleId);
    }
    
    @Override
    public List<Task> getTasksByParentId(Integer roleId, Integer parentId) {
        return taskRepository.getTasksByParentId(roleId, parentId);
    }
    
    @Override
    public Integer taskNameBelongForThisUser(String taskName, Integer roleId) {
        return taskRepository.taskNameBelongForThisUser(taskName, roleId);
    }
    
}
