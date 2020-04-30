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
        border.setSource(new ThemeResource("../../images/pages/main/border.jpg"));
        borderPanel.setContent(border);
        addComponent(borderPanel);
        
        Panel bottomPanel = new Panel();
        bottomPanel.setHeight("300px");
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
        dragon.setSource(new ThemeResource("../../images/pages/main/dragon.png"));
        dragon.setWidth("300px");
        dragon.setHeight("250px");
        bottomHL.addComponent(dragon);
        bottomHL.setComponentAlignment(dragon,Alignment.MIDDLE_LEFT);
        bottomHL.setExpandRatio(dragon,1);
        
        bottomPanel.setContent(bottomHL);
        addComponent(bottomPanel);
        
        Panel dragonPanel = new Panel();
        
        HorizontalLayout dragonsHL = new HorizontalLayout();
        dragonsHL.setSizeFull();
        dragonsHL.setSpacing(true);
        dragonsHL.setMargin(true);
    
        Embedded first = new Embedded();
        first.setSource(new ThemeResource("../../images/pages/main/1-dragon.png"));
        first.setHeight("100px");
        dragonsHL.addComponent(first);
        dragonsHL.setComponentAlignment(first,Alignment.MIDDLE_CENTER);
        dragonsHL.setExpandRatio(first,5);
        addArrow(dragonsHL);
    
        Embedded second = new Embedded();
        second.setSource(new ThemeResource("../../images/pages/main/2-dragon.png"));
        second.setHeight("200px");
        dragonsHL.addComponent(second);
        dragonsHL.setComponentAlignment(second,Alignment.MIDDLE_CENTER);
        dragonsHL.setExpandRatio(second,7);
        addArrow(dragonsHL);
    
        Embedded third = new Embedded();
        third.setSource(new ThemeResource("../../images/pages/main/3-dragon.png"));
        third.setSizeFull();
        dragonsHL.addComponent(third);
        dragonsHL.setComponentAlignment(third,Alignment.MIDDLE_CENTER);
        dragonsHL.setExpandRatio(third,9);
        addArrow(dragonsHL);
    
        Embedded last = new Embedded();
        last.setSource(new ThemeResource("../../images/pages/main/4-dragon.png"));
        last.setHeight("350px");
        dragonsHL.addComponent(last);
        dragonsHL.setComponentAlignment(last,Alignment.MIDDLE_CENTER);
        dragonsHL.setExpandRatio(last,20);
        
        dragonPanel.setContent(dragonsHL);
        addComponent(dragonPanel);
    }
    
    private void addArrow(HorizontalLayout dragonsHL) {
        Embedded arrow=new Embedded();
        arrow.setSource(new ThemeResource("../../images/pages/main/arrow.png"));
        arrow.setSizeFull();
        dragonsHL.addComponent(arrow);
        dragonsHL.setComponentAlignment(arrow,Alignment.MIDDLE_CENTER);
        dragonsHL.setExpandRatio(arrow,1);
    }
    
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        removeAllComponents();
        addLayout();
    }
    
}
