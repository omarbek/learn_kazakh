package kz.omar.ui.main;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import kz.omar.ui.commons.LearnKazakhMainUI;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-26
 * @project learn_kazakh
 */
@SpringView(name = MainLayoutFactory.NAME, ui = LearnKazakhMainUI.class)
public class MainLayoutFactory extends VerticalLayout implements View {
    
    public static final String NAME = "main";
    
    private void addLayout() {
        setMargin(true);
        
        Panel panel = new Panel();
        
        Embedded logo = new Embedded();
        logo.setSource(new ThemeResource("../../images/kazakh.png"));
        
        panel.setContent(logo);
        
        addComponent(panel);
    }
    
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        removeAllComponents();
        addLayout();
    }
    
}
