package kz.omar.ui.pages.common;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import kz.omar.service.task.TaskService;
import kz.omar.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Omarbek.Dinassil
 * on 2020-05-02
 * @project learn_kazakh
 */
public abstract class PageLayout extends VerticalLayout implements View {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private TaskService taskService;
    
    private String taskName;
    
    public PageLayout(String taskName) {
        this.taskName = taskName;
    }
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        removeAllComponents();
        
        Integer roleId = userService.getCurrentUser().getRole().getRoleId();
        Integer i = taskService.taskNameBelongForThisUser(taskName, roleId);
        if (i == null) {
            Notification.show("У вас нет доступа");
            return;
        }
        
        addLayout();
    }
    
    protected abstract void addLayout();
    
}
