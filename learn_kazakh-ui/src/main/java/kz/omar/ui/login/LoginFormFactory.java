package kz.omar.ui.login;


import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import kz.omar.utils.ButtonUtils;
import kz.omar.utils.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-25
 * @project learn_kazakh
 */
@org.springframework.stereotype.Component
public class LoginFormFactory {
    
    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;
    
    private class LoginForm {
        private VerticalLayout root;
        private Panel panel;
        private TextField username;
        private PasswordField password;
        private Button loginButton;
        private Button signupButton;
        
        public LoginForm init() {//Builder pattern
            root = new VerticalLayout();
            root.setMargin(true);
            root.setHeight("100%");
            
            panel = new Panel();
            panel.setSizeUndefined();
            
            loginButton = new Button(ButtonUtils.LOGIN.toString());
            loginButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
            
            signupButton = new Button(ButtonUtils.SIGN_UP.toString());
            signupButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
            
            username = new TextField(FieldUtils.USER_NAME.toString());
            password = new PasswordField(FieldUtils.PASSWORD.toString());
            
            return this;
        }
        
        public Component layout() {
            root.addComponent(panel);
            root.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
            
            FormLayout loginLayout = new FormLayout();
            loginLayout.addComponent(username);
            loginLayout.addComponent(password);
            
            loginLayout.addComponent(new HorizontalLayout(loginButton, signupButton));
            loginLayout.setSizeUndefined();
            loginLayout.setMargin(true);
            
            loginButton.addClickListener(new Button.ClickListener() {
                public void buttonClick(Button.ClickEvent clickEvent) {
                    try {
                        Authentication auth = new UsernamePasswordAuthenticationToken(username.getValue(),
                                password.getValue());
                        Authentication authentication = daoAuthenticationProvider.authenticate(auth);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        
                        UI.getCurrent().getPage().setLocation("/learn_kazakh/ui");
                    } catch (AuthenticationException e) {
                        Notification.show("Error!", "Login fail! Try again", Notification.Type.ERROR_MESSAGE);
                    }
                    username.clear();
                    password.clear();
                }
            });
            
            signupButton.addClickListener(new Button.ClickListener() {
                public void buttonClick(Button.ClickEvent clickEvent) {
                    UI.getCurrent().getPage().setLocation("/learn_kazakh/signup");
                }
            });
            
            panel.setContent(loginLayout);
            
            return root;
        }
    }
    
    public Component createComponent() {
        return new LoginForm().init().layout();
    }
    
}
