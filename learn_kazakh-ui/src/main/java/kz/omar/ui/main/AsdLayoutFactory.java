package kz.omar.ui.main;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
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
    
        VerticalLayout vertical = new VerticalLayout();
        vertical.addComponent(new Label("Am the Hint..."));
    
        PopupView popup = new PopupView(null,vertical);
    
        Button b = new Button("Show Hint");
        b.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        b.addClickListener((Button.ClickEvent event) -> {
            popup.setPopupVisible(true);
        });
        addComponents(b, popup);
    }
    
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        removeAllComponents();
        addLayout();
    }
    
}
