package kz.omar.ui.commons;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import kz.omar.model.entity.Task;
import kz.omar.service.task.TaskService;
import kz.omar.service.user.UserService;
import kz.omar.ui.start.UIComponentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-29
 * @project learn_kazakh
 */
@Component
public class FooterFactory implements UIComponentBuilder {
    
    private FooterMenuLayout footerMenuLayout;
    
    private List<Task> footerTasks;
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private UserService userService;
    
    private class FooterMenuLayout extends VerticalLayout {
        
        public FooterMenuLayout init() {
            footerTasks = taskService.getFooterTasks();
            return this;
        }
        
        public FooterMenuLayout load() {
            return this;
        }
        
        public void layout() {
            removeAllComponents();
            
            VerticalLayout rootVL = new VerticalLayout();
            rootVL.setSizeFull();
            
            Panel aboutPanel = new Panel();
            aboutPanel.addStyleName("footer-about");
            
            VerticalLayout aboutVL = new VerticalLayout();
            aboutVL.setSizeFull();
            
            HorizontalLayout linksHL = new HorizontalLayout();
            linksHL.addStyleName("horizontal-menu");
            linksHL.setWidth("75%");
            linksHL.setHeight("100px");
            
            for (Task task: footerTasks) {
                Button menuButton = new Button(task.getName());
                menuButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
                menuButton.addClickListener((Button.ClickEvent event) -> {
                    Notification.show("s");//todo
                });
                menuButton.addStyleName("menu");
                
                linksHL.addComponent(menuButton);
                linksHL.setComponentAlignment(menuButton, Alignment.MIDDLE_CENTER);
            }
            
            aboutVL.addComponent(linksHL);
            aboutVL.setComponentAlignment(linksHL, Alignment.MIDDLE_CENTER);
            
            Label aboutLabel = new Label("<h3>Sed ut perspiciatis unde omnis iste natus error sit voluptatem " +
                    "accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore " +
                    "veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem " +
                    "quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur.</h3>");//todo
            aboutLabel.setContentMode(ContentMode.HTML);
            aboutLabel.setWidth("50%");
            aboutLabel.setHeight("100px");
            aboutVL.addComponent(aboutLabel);
            aboutVL.setComponentAlignment(aboutLabel, Alignment.MIDDLE_CENTER);
            
            aboutPanel.setContent(aboutVL);
            rootVL.addComponent(aboutPanel);
            
            Panel copyrightPanel = new Panel();
            copyrightPanel.addStyleName("footer-copyright");
            
            HorizontalLayout copyrightHL = new HorizontalLayout();
            copyrightHL.setWidth("100%");
            copyrightHL.setHeight("50px");
            Label contentLabel = new Label("<center><b>© 2020 Copyright: Динасил О.Б.</center></b>", ContentMode.HTML);
            contentLabel.setStyleName("footer");
            copyrightHL.addComponent(contentLabel);
            copyrightHL.setComponentAlignment(contentLabel, Alignment.MIDDLE_CENTER);
            
            copyrightPanel.setContent(copyrightHL);
            
            rootVL.addComponent(copyrightPanel);
            
            addComponent(rootVL);
        }
    }
    
    @Override
    public void createComponent() {
        footerMenuLayout.init().load().layout();
    }
    
    public FooterFactory() {
        footerMenuLayout = new FooterMenuLayout();
    }
    
    public FooterMenuLayout getFooterMenuLayout() {
        return footerMenuLayout;
    }
}
