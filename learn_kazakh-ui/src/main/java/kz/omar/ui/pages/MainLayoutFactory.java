package kz.omar.ui.pages;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import kz.omar.ui.start.LearnKazakhMainUI;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-26
 * @project learn_kazakh
 */
@SpringView(name = MainLayoutFactory.NAME, ui = LearnKazakhMainUI.class)
public class MainLayoutFactory extends VerticalLayout implements View {
    
    public static final String NAME = "главная";
    
    private void addLayout() {
        setMargin(true);
        setWidth("100%");
        
        Panel borderPanel = new Panel();
        Embedded border = new Embedded();
        border.setSource(new ThemeResource("../../images/border.jpg"));
        borderPanel.setContent(border);
        addComponent(borderPanel);
        
        Panel bottomPanel = new Panel();
        HorizontalLayout bottomHL=new HorizontalLayout();
        bottomHL.setSizeFull();
    
        Label welcomeLabel = new Label();
        welcomeLabel.setValue("<h1>Добро пожаловать!</h1>" +
                "<p>Здесь ты будешь учить все самое нужное и необходимое " +
                "для понимания и освоения основ казахской речи." +
                "<br>" +
                "Чем больше слов ты изучишь, тем больше " +
                "станет твой дракон!</p>");
        welcomeLabel.setContentMode(ContentMode.HTML);
        welcomeLabel.setStyleName("main-page");
        bottomHL.addComponent(welcomeLabel);
        bottomHL.setExpandRatio(welcomeLabel,2);
        bottomHL.setComponentAlignment(welcomeLabel,Alignment.MIDDLE_RIGHT);
        
        
        Embedded dragon = new Embedded();
        dragon.setSource(new ThemeResource("../../images/asd.png"));
        dragon.setWidth("300px");
        dragon.setHeight("250px");
        bottomHL.addComponent(dragon);
        bottomHL.setComponentAlignment(dragon,Alignment.MIDDLE_LEFT);
        bottomHL.setExpandRatio(dragon,1);
        
        bottomPanel.setContent(bottomHL);
        addComponent(bottomPanel);
    }
    
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        removeAllComponents();
        addLayout();
    }
    
}
