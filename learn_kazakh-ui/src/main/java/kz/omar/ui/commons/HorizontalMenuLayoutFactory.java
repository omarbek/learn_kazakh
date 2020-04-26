package kz.omar.ui.commons;


import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

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
    
    public HorizontalMenuLayoutFactory(){
        logoLayout=new LogoLayout();
    }
    
    public LogoLayout getLogoLayout() {
        return logoLayout;
    }
    
    private class LogoLayout extends VerticalLayout {//Builder pattern
        
        private MenuBar menuBar;
        
        public LogoLayout init() {
            menuBar = new MenuBar();
            return this;
        }
        
        public LogoLayout layout() {
            
            MenuBar.MenuItem sss = menuBar.addItem("sss",
                    new ThemeResource("../../themes/univercity/img/book.png"), new MenuBar.Command() {
                public void menuSelected(MenuBar.MenuItem menuItem) {
                    learnKazakhMenuFactory.setMenu(menuItem.getText());
                    learnKazakhMenuFactory.setPath("main");
                    learnKazakhMenuFactory.createComponent();
                }
            });
            MenuBar.MenuItem qwe = menuBar.addItem("qwe",
                    new ThemeResource("../../themes/univercity/img/info.png"), new MenuBar.Command() {
                        public void menuSelected(MenuBar.MenuItem menuItem) {
                            learnKazakhMenuFactory.setMenu(menuItem.getText());
                            learnKazakhMenuFactory.setPath("asd");
                            learnKazakhMenuFactory.createComponent();
                        }
                    });
            
            addComponent(menuBar);
            setComponentAlignment(menuBar, Alignment.MIDDLE_CENTER);
            
            return this;
        }
        
    }
    
    public void createComponent() {
        logoLayout.init().layout();
    }
    
}
