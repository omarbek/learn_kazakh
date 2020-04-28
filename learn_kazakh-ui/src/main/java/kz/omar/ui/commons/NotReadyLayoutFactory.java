package kz.omar.ui.commons;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import kz.omar.ui.start.LearnKazakhMainUI;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-26
 * @project learn_kazakh
 */
@SpringView(name = NotReadyLayoutFactory.NAME, ui = LearnKazakhMainUI.class)
public class NotReadyLayoutFactory extends VerticalLayout implements View {
    
    public static final String NAME = "неготово";
    
    private void addLayout() {
        setMargin(true);
        
        Panel panel = new Panel();
        panel.setContent(new Label("Извините пока не готово"));
        
        addComponent(panel);
    }
    
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        removeAllComponents();
        addLayout();
    }
    
}
