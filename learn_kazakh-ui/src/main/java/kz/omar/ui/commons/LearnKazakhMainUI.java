package kz.omar.ui.commons;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import kz.omar.navigator.LearnKazakhNavigator;
import kz.omar.ui.main.MainLayoutFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-25
 * @project learn_kazakh
 */
@SpringUI(path = LearnKazakhMainUI.NAME)
@Title("Learn Kazakh")
@Theme("kazakh")
public class LearnKazakhMainUI extends UI {
    
    public static final String NAME = "/ui";
    
    private Panel changePanel = new Panel();
    
    @Autowired
    private HorizontalMenuFactory horizontalMenuFactory;//DI
    
    @Autowired
    private VerticalMenuFactory verticalMenuFactory;
    
    @Autowired
    private ApplicationContext applicationContext;
    
    @Autowired
    private SpringViewProvider springViewProvider;
    
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout rootLayout = new VerticalLayout();
        rootLayout.setSizeFull();
//        rootLayout.setMargin(true);
        rootLayout.addStyleName("with-image");
    
        Panel horMenuPanel = new Panel();
        horMenuPanel.setWidth("100%");
        horMenuPanel.setHeight("64px");
    
        horizontalMenuFactory.createComponent();
        Component horizontalMenuComponent = horizontalMenuFactory.getHorizontalMenuLayout();//Builder pattern
        
        horMenuPanel.setContent(horizontalMenuComponent);
        
        rootLayout.addComponent(horMenuPanel);
        rootLayout.setComponentAlignment(horMenuPanel, Alignment.TOP_CENTER);
        
        Panel bottomPanel = new Panel();
        bottomPanel.setWidth("100%");
        bottomPanel.setHeight("100%");
        bottomPanel.addStyleName("no-color");
        
        HorizontalLayout bottomHL = new HorizontalLayout();
        bottomHL.setSizeFull();
        bottomHL.setMargin(true);
        
        verticalMenuFactory.createComponent();
        Component verticalMenuComponent = verticalMenuFactory.getVerticalMenu();
        bottomHL.addComponent(verticalMenuComponent);
        bottomHL.setComponentAlignment(verticalMenuComponent, Alignment.TOP_CENTER);
        bottomHL.setExpandRatio(verticalMenuComponent, 1);
    
        changePanel.setHeight("100%");
        changePanel.addStyleName("no-color");
        bottomHL.addComponent(changePanel);
        bottomHL.setComponentAlignment(changePanel, Alignment.TOP_CENTER);
        bottomHL.setExpandRatio(changePanel, 4);
        
        bottomPanel.setContent(bottomHL);
        
        rootLayout.addComponent(bottomPanel);
        rootLayout.setComponentAlignment(bottomPanel, Alignment.MIDDLE_CENTER);
        rootLayout.setExpandRatio(bottomPanel, 1);
        
        initNavigator();
        
        setContent(rootLayout);
    }
    
    private void initNavigator() {
        LearnKazakhNavigator navigator = new LearnKazakhNavigator(this, changePanel);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(navigator);
        navigator.addProvider(springViewProvider);
        navigator.navigateTo(MainLayoutFactory.NAME);
    }
    
}
