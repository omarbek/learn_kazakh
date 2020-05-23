package kz.omar.ui.pages.common;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import kz.omar.service.task.TaskService;
import kz.omar.service.user.UserService;
import kz.omar.utils.NotificationUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Omarbek.Dinassil
 * on 2020-05-02
 * @project learn_kazakh
 */
public abstract class AbstractPageFactory extends VerticalLayout implements View {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private TaskService taskService;
    
    private String taskName;
    
    public AbstractPageFactory(String taskName) {
        this.taskName = taskName;
    }
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        removeAllComponents();
        
        Integer roleId = userService.getCurrentUser().getRole().getRoleId();
        Integer access = taskService.taskNameBelongForThisUser(taskName, roleId);
        if (access == null) {
            Notification.show(NotificationUtils.NO_ACCESS.toString(), Notification.Type.WARNING_MESSAGE);
            return;
        }
        
        addLayout();
    }
    
    protected abstract void addLayout();
    
}
