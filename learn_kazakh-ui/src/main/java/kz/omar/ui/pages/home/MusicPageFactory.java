package kz.omar.ui.pages.home;

import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import kz.omar.ui.pages.common.AbstractPageFactory;
import kz.omar.ui.start.LearnKazakhUI;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-26
 * @project learn_kazakh
 */
@SpringView(name = MusicPageFactory.NAME, ui = LearnKazakhUI.class)
public class MusicPageFactory extends AbstractPageFactory {
    
    static final String NAME = "музыка";
    
    MusicPageFactory() {
        super(NAME);
    }
    
    @Override
    public void addLayout() {
        setMargin(true);
        
        VerticalLayout vertical = new VerticalLayout();
        vertical.addComponent(new Label("Am the Hint..."));
        
        PopupView popup = new PopupView(null, vertical);
        
        Button b = new Button("Музыка");
        b.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        b.addClickListener((Button.ClickEvent event) -> {
            popup.setPopupVisible(true);
        });
        addComponents(b, popup);
    }
    
}
