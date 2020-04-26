package kz.omar.ui.main;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import kz.omar.ui.commons.LearnKazakhMainUI;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-26
 * @project learn_kazakh
 */
@SpringView(name = AsdLayoutFactory.NAME, ui = LearnKazakhMainUI.class)
public class AsdLayoutFactory extends VerticalLayout implements View {
    
    public static final String NAME = "asd";
    
    private void addLayout() {
        setMargin(true);
        
        Panel panel = new Panel();
        panel.setContent(new Label("asd"));
        
        addComponent(panel);
    }
    
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        removeAllComponents();
        addLayout();
    }
    
}
