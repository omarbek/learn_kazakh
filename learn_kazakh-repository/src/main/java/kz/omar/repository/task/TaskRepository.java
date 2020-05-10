package kz.omar.repository.task;

import kz.omar.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-26
 * @project learn_kazakh
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    
    @Query("select t from Task t" +
            " where t.parent is null" +
            " and (t.role.id = :roleId or t.common = 1)")
    List<Task> getTasksWithNoParentByRoleId(@Param("roleId") Integer roleId);
    
    @Query("select t from Task t" +
            " where t.parent.id = :parentId" +
            " and (t.role.id = :roleId or t.common = 1)")
    List<Task> getTasksByParentId(@Param("roleId") Integer roleId, @Param("parentId") Integer parentId);
    
    @Query("select 1 from Task t" +
            " where t.name = :taskName" +
            " and (t.role.id = :roleId or t.common = 1)")
    Integer taskNameBelongForThisUser(@Param("taskName") String taskName, @Param("roleId") Integer roleId);
    
    @Query("select t from Task t where t.common = 1 and t.parent is null")
    List<Task> getFooterTasks();
    
}
