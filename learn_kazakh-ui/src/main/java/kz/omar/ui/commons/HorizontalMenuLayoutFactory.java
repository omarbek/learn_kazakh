package kz.omar.ui.commons;


import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import kz.omar.model.entity.Task;
import kz.omar.model.entity.User;
import kz.omar.service.task.TaskService;
import kz.omar.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

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
    private VerticalMenuFactory learnKazakhMenuFactory;
    
    private HorizontalMenuLayout horizontalMenuLayout;
    
    private List<Task> tasks;
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private UserService userService;
    
    private class HorizontalMenuLayout extends HorizontalLayout {//Builder pattern
        
        private MenuBar menuBar;
        
        HorizontalMenuLayout init() {
            setWidth("100%");
            setHeightUndefined();
            setMargin(true);
            addStyleName("horizontal-menu");
            
            tasks = new ArrayList<>();
            
            menuBar = new MenuBar();
            menuBar.setHeight("32px");
            
            return this;
        }
        
        HorizontalMenuLayout load() {
            User user = userService.getCurrentUser();
            Integer roleId = user.getRole().getRoleId();
            tasks = taskService.getTasksWithNoParentByRoleId(roleId);
            learnKazakhMenuFactory.setMenuParentId(tasks.get(0).getTaskId());
            return this;
        }
        
        public HorizontalMenuLayout layout() {
            removeAllComponents();
            
            HorizontalLayout menuVL = new HorizontalLayout();
            int width = tasks.size() * 15;
            menuVL.setWidth(width + "%");
            for (Task task: tasks) {
                Button menuButton = new Button(task.getName());
                menuButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
                //                menuButton.setIcon(new ThemeResource("../../themes/kazakh/img/book.png"));
                menuButton.addClickListener((Button.ClickEvent event) -> {
                    learnKazakhMenuFactory.setMenuParentId(task.getTaskId());
                    learnKazakhMenuFactory.setPath(task.getNavigatePath());
                    learnKazakhMenuFactory.createComponent();
                });
                menuButton.addStyleName("qwe");
                
                menuVL.addComponent(menuButton);
            }
            addComponent(menuVL);
            setComponentAlignment(menuVL, Alignment.MIDDLE_LEFT);
            
            MenuBar.MenuItem myProfileMI = menuBar.addItem("",
                    new ThemeResource("../../themes/kazakh/img/user_on.png"), null);
            myProfileMI.addSeparator();
            
            myProfileMI.addItem("Мой профиль",
                    new ThemeResource("../../themes/kazakh/img/user_on.png"), new MenuBar.Command() {
                        public void menuSelected(MenuBar.MenuItem menuItem) {
                        }
                    });
            myProfileMI.addItem("Mой прогресс",
                    new ThemeResource("../../themes/kazakh/img/button/apply.png"), new MenuBar.Command() {
                        public void menuSelected(MenuBar.MenuItem menuItem) {
                        }
                    });
            myProfileMI.addItem("Mой словарь",
                    new ThemeResource("../../themes/kazakh/img/book.png"), new MenuBar.Command() {
                        public void menuSelected(MenuBar.MenuItem menuItem) {
                        }
                    });
            myProfileMI.addItem("Выйти",
                    new ThemeResource("../../themes/kazakh/img/button/exit.png"), new MenuBar.Command() {
                        public void menuSelected(MenuBar.MenuItem menuItem) {
                            SecurityContextHolder.clearContext();
                            UI.getCurrent().getPage().setLocation("/learn_kazakh/login");
                        }
                    });
            
            addComponent(menuBar);
            setComponentAlignment(menuBar, Alignment.MIDDLE_RIGHT);
            
            return this;
        }
        
    }
    
    public void createComponent() {
        horizontalMenuLayout.init().load().layout();
    }
    
    public HorizontalMenuLayoutFactory() {
        horizontalMenuLayout = new HorizontalMenuLayout();
    }
    
    public HorizontalMenuLayout getHorizontalMenuLayout() {
        return horizontalMenuLayout;
    }
    
}
