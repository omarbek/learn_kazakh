package kz.omar.ui.login;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import kz.omar.service.security.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-25
 * @project learn_kazakh
 */
@org.springframework.stereotype.Component
public class SignupFormFactory {
    
    @Autowired
    private RegisterUserService registerUserService;
    
    private class SignupForm {
        
        private VerticalLayout root;
        private Panel panel;
        private TextField username;
        private PasswordField passwordField;
        private PasswordField passwordAgainField;
        private Button saveButton;
        
        public SignupForm init() {
            root = new VerticalLayout();
            root.setMargin(true);
            root.setHeight("100%");
            
            panel = new Panel("Signup");
            panel.setSizeUndefined();
            
            saveButton = new Button("Save");
            saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
            
            username = new TextField("Username");
            passwordField = new PasswordField("Password");
            passwordAgainField = new PasswordField("Password again");
            
            saveButton.addClickListener(new Button.ClickListener() {
                public void buttonClick(Button.ClickEvent clickEvent) {
                    if (!passwordAgainField.getValue().equals(passwordField.getValue())) {
                        Notification.show("Error", "Passwords do not match", Notification.Type.ERROR_MESSAGE);
                        return;
                    }
                    registerUserService.save(username.getValue(), passwordField.getValue());
                    UI.getCurrent().getPage().setLocation("/learn_kazakh/login");
                }
            });
            
            return this;
        }
        
        public Component layout() {
            root.addComponent(panel);
            root.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
            
            FormLayout signupLayout = new FormLayout();
            signupLayout.addComponent(username);
            signupLayout.addComponent(passwordField);
            signupLayout.addComponent(passwordAgainField);
            
            signupLayout.addComponent(saveButton);
            
            signupLayout.setSizeUndefined();
            signupLayout.setMargin(true);
            
            panel.setContent(signupLayout);
            
            return root;
        }
        
    }
    
    public Component createComponent() {
        return new SignupForm().init().layout();
    }
    
}
