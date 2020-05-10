package kz.omar.ui.commons;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import kz.omar.model.entity.Task;
import kz.omar.model.entity.User;
import kz.omar.navigator.LearnKazakhNavigator;
import kz.omar.service.task.TaskService;
import kz.omar.service.user.UserService;
import kz.omar.ui.start.UIComponentBuilder;
import org.springframework.beans.factory.annotation.Autowired;

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
    
    private class VerticalMenu extends VerticalLayout {
        
        private VerticalLayout mainMenuVL;
        private List<Task> tasks;
        
        VerticalMenu init() {
            mainMenuVL = new VerticalLayout();
            mainMenuVL.setSizeFull();
            return this;
        }
        
        VerticalMenu load() {
            User user = userService.getCurrentUser();
            Integer roleId = user.getRole().getRoleId();
            tasks = taskService.getTasksByParentId(roleId, menuParentId);
            return this;
        }
        
        public VerticalMenu layout() {
            setWidth("70%");
            setHeightUndefined();
            
            for (Task task: tasks) {
                String taskName = task.getName();
                
                Button menuButton = new Button(taskName);
                menuButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
                String icon = task.getIconPath();
                if (icon != null) {
                    menuButton.setIcon(new ThemeResource("../../images/menu/" + icon));
                }
                menuButton.addClickListener((Button.ClickEvent event) -> {
                    if (taskName == null) {
                        return;
                    }
                    
                    LearnKazakhNavigator.navigate(taskName);
                });
                menuButton.addStyleName("vertical-menu");
                
                mainMenuVL.addComponent(menuButton);
                mainMenuVL.setComponentAlignment(menuButton, Alignment.MIDDLE_LEFT);
            }
            
            addComponent(mainMenuVL);
            setComponentAlignment(mainMenuVL, Alignment.MIDDLE_CENTER);
            
            return this;
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
