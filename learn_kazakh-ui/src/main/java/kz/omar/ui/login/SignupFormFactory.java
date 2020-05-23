package kz.omar.ui.login;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import kz.omar.model.entity.Role;
import kz.omar.service.role.RoleService;
import kz.omar.service.user.UserService;
import kz.omar.utils.ButtonUtils;
import kz.omar.utils.FieldUtils;
import kz.omar.utils.NotificationUtils;
import kz.omar.utils.TitleUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-25
 * @project learn_kazakh
 */
@org.springframework.stereotype.Component
public class SignupFormFactory {
    
    @Autowired
    private UserService registerUserService;
    
    @Autowired
    private RoleService roleService;
    
    private class SignupForm {
        
        private VerticalLayout root;
        private Panel panel;
        
        private TextField username;
        private PasswordField passwordField;
        private PasswordField passwordAgainField;
        private ComboBox rolesCB;
        
        private Button saveButton;
        
        public SignupForm init() {
            root = new VerticalLayout();
            root.setMargin(true);
            root.setHeight("100%");
            
            panel = new Panel(TitleUtils.SIGN_UP.toString());
            panel.setSizeUndefined();
            
            username = new TextField(FieldUtils.USER_NAME.toString());
            passwordField = new PasswordField(FieldUtils.PASSWORD.toString());
            passwordAgainField = new PasswordField(FieldUtils.PASSWORD_AGAIN.toString());
            rolesCB = new ComboBox(FieldUtils.ROLE.toString());
            rolesCB.setWidth("100%");
            
            saveButton = new Button(ButtonUtils.SAVE.toString());
            saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
            saveButton.addClickListener(new Button.ClickListener() {
                public void buttonClick(Button.ClickEvent clickEvent) {
                    if (!passwordAgainField.getValue().equals(passwordField.getValue())) {
                        Notification.show(NotificationUtils.ERROR.toString(),
                                NotificationUtils.PASSWORDS_DO_NOT_MATCH.toString(), Notification.Type.ERROR_MESSAGE);
                        return;
                    }
                    registerUserService.save(username.getValue(), passwordField.getValue(), (Role) rolesCB.getValue());
                    UI.getCurrent().getPage().setLocation("/learn_kazakh/login");
                }
            });
            
            return this;
        }
        
        public SignupForm load() {
            List<Role> universities = roleService.getRoles();
            rolesCB.addItems(universities);
            
            return this;
        }
        
        public Component layout() {
            root.addComponent(panel);
            root.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
            
            FormLayout signupLayout = new FormLayout();
            signupLayout.addComponent(username);
            signupLayout.addComponent(passwordField);
            signupLayout.addComponent(passwordAgainField);
            signupLayout.addComponent(rolesCB);
            
            signupLayout.addComponent(saveButton);
            
            signupLayout.setSizeUndefined();
            signupLayout.setMargin(true);
            
            panel.setContent(signupLayout);
            
            return root;
        }
        
    }
    
    public Component createComponent() {
        return new SignupForm().init().load().layout();
    }
    
}
