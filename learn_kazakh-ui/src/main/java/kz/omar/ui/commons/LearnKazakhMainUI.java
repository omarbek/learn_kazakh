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
@Theme("valo")
public class LearnKazakhMainUI extends UI {
    
    public static final String NAME = "/ui";
    
    private Panel changeTab = new Panel();
    
    @Autowired
    private HorizontalMenuLayoutFactory horizontalMenu;//DI
    
    @Autowired
    private LearnKazakhMenuFactory learnKazakhMenuFactory;
    
    @Autowired
    private ApplicationContext applicationContext;
    
    @Autowired
    private SpringViewProvider springViewProvider;
    
    protected void init(VaadinRequest vaadinRequest) {
        changeTab.setHeight("100%");
        
        VerticalLayout rootLayout = new VerticalLayout();
        rootLayout.setSizeFull();
        rootLayout.setMargin(true);
        
        Panel contentPanel = new Panel();
        contentPanel.setWidth("75%");
        contentPanel.setHeight("100%");
        
        Panel logoPanel = new Panel();
        logoPanel.setWidth("75%");
        logoPanel.setHeightUndefined();
        
        HorizontalLayout uiLayout = new HorizontalLayout();
        uiLayout.setSizeFull();
        uiLayout.setMargin(true);
        
        horizontalMenu.createComponent();
        learnKazakhMenuFactory.createComponent();
        Component horizontalMenuComponent = horizontalMenu.getLogoLayout();//Builder pattern
        Component menu = learnKazakhMenuFactory.getLearnKazakhMenu();
        
        uiLayout.addComponent(menu);
        uiLayout.addComponent(changeTab);
        
        uiLayout.setComponentAlignment(changeTab, Alignment.TOP_CENTER);
        uiLayout.setComponentAlignment(menu, Alignment.TOP_CENTER);
        
        uiLayout.setExpandRatio(menu, 1);
        uiLayout.setExpandRatio(changeTab, 2);
        
        logoPanel.setContent(horizontalMenuComponent);
        contentPanel.setContent(uiLayout);
        
        rootLayout.addComponent(logoPanel);
        rootLayout.addComponent(contentPanel);
        rootLayout.setComponentAlignment(contentPanel, Alignment.MIDDLE_CENTER);
        rootLayout.setComponentAlignment(logoPanel, Alignment.TOP_CENTER);
        rootLayout.setExpandRatio(contentPanel, 1);
        
        initNavigator();
        
        setContent(rootLayout);
    }
    
    private void initNavigator() {
        LearnKazakhNavigator navigator = new LearnKazakhNavigator(this, changeTab);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(navigator);
        navigator.addProvider(springViewProvider);
        navigator.navigateTo(MainLayoutFactory.NAME);
    }
    
}
