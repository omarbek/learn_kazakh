package kz.omar.ui.commons;

import com.vaadin.data.Property;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import kz.omar.model.entity.Task;
import kz.omar.model.entity.User;
import kz.omar.navigator.LearnKazakhNavigator;
import kz.omar.service.task.TaskService;
import kz.omar.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-25
 * @project learn_kazakh
 */
@org.springframework.stereotype.Component
public class VerticalMenuFactory implements UIComponentBuilder {
    
    private VerticalMenu verticalMenu;
    private Integer menuParentId;
    private String path;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private TaskService taskService;
    
    public VerticalMenuFactory() {
        verticalMenu = new VerticalMenu();
    }
    
    private class VerticalMenu extends VerticalLayout implements Property.ValueChangeListener {
        
        private Tree mainMenu;
        private List<Task> tasks;
        
        VerticalMenu init() {
            mainMenu = new Tree();
            mainMenu.addValueChangeListener(this);
            return this;
        }
        
        VerticalMenu load() {
            User user = userService.getCurrentUser();
            Integer roleId = user.getRole().getRoleId();
            tasks = taskService.getTasksByParentId(roleId, menuParentId);
            return this;
        }
        
        public VerticalMenu layout() {
            setWidth("100%");
            setHeightUndefined();
            
            for (Task task: tasks) {
                String taskName = task.getName();
                mainMenu.addItem(taskName);
                mainMenu.expandItem(taskName);
                mainMenu.setChildrenAllowed(taskName, false);
            }
            
            addComponent(mainMenu);
            
            return this;
        }
        
        public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
            String selectedItemPath = (String) valueChangeEvent.getProperty().getValue();
            
            if (selectedItemPath == null) {
                return;
            }
            
            String path = selectedItemPath.toLowerCase().replaceAll("\\s+", "");
            LearnKazakhNavigator.navigate(path);
        }
    }
    
    public void createComponent() {
        verticalMenu.removeAllComponents();
        verticalMenu.init().load().layout();
        LearnKazakhNavigator.navigate(path);
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public VerticalMenu getVerticalMenu() {
        return verticalMenu;
    }
    
    public void setMenuParentId(Integer menuParentId) {
        this.menuParentId = menuParentId;
    }
    
}