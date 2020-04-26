package kz.omar.ui.commons;


import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;
import kz.omar.model.entity.Task;
import kz.omar.model.entity.User;
import kz.omar.service.task.TaskService;
import kz.omar.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-25
 * @project learn_kazakh
 */
@org.springframework.stereotype.Component
public class HorizontalMenuLayoutFactory implements UIComponentBuilder {
    
    @Autowired
    private LearnKazakhMenuFactory learnKazakhMenuFactory;
    
    private LogoLayout logoLayout;
    
    private List<Task> tasks;
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private UserService userService;
    
    private class LogoLayout extends VerticalLayout {//Builder pattern
        
        private MenuBar menuBar;
        
        LogoLayout init() {
            addStyleName("blue");
            tasks = new ArrayList<>();
            menuBar = new MenuBar();
            return this;
        }
        
        LogoLayout load() {
            User user = userService.getCurrentUser();
            Integer roleId = user.getRole().getRoleId();
            tasks = taskService.getTasksWithNoParentByRoleId(roleId);
            learnKazakhMenuFactory.setMenuParentId(tasks.get(0).getTaskId());
            return this;
        }
        
        public LogoLayout layout() {
            for (Task task: tasks) {
                menuBar.addItem(task.getName(),
                        new ThemeResource("../../themes/kazakh/img/book.png"), new MenuBar.Command() {
                            public void menuSelected(MenuBar.MenuItem menuItem) {
                                learnKazakhMenuFactory.setMenuParentId(task.getTaskId());
                                learnKazakhMenuFactory.setPath(task.getNavigatePath());
                                learnKazakhMenuFactory.createComponent();
                            }
                        });
            }
    
            MenuBar.MenuItem myProfileMI = menuBar.addItem("",
                    new ThemeResource("../../themes/kazakh/img/user_on.png"), null);
            myProfileMI.addSeparator();
            
            myProfileMI.addItem("My profile",
                    new ThemeResource("../../themes/kazakh/img/user_on.png"), new MenuBar.Command() {
                        public void menuSelected(MenuBar.MenuItem menuItem) {
                        }
                    });
            myProfileMI.addItem("My progress",
                    new ThemeResource("../../themes/kazakh/img/button/apply.png"), new MenuBar.Command() {
                        public void menuSelected(MenuBar.MenuItem menuItem) {
                        }
                    });
            myProfileMI.addItem("Logout",
                    new ThemeResource("../../themes/kazakh/img/button/exit.png"), new MenuBar.Command() {
                        public void menuSelected(MenuBar.MenuItem menuItem) {
                        }
                    });
    
            removeAllComponents();
            addComponent(menuBar);
            setComponentAlignment(menuBar, Alignment.MIDDLE_CENTER);
            
            return this;
        }
        
    }
    
    public void createComponent() {
        logoLayout.init().load().layout();
    }
    
    public HorizontalMenuLayoutFactory() {
        logoLayout = new LogoLayout();
    }
    
    public LogoLayout getLogoLayout() {
        return logoLayout;
    }
    
}
