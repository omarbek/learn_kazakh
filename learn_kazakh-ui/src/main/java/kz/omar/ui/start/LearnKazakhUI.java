package kz.omar.ui.start;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import kz.omar.navigator.LearnKazakhNavigator;
import kz.omar.ui.commons.FooterFactory;
import kz.omar.ui.commons.HorizontalMenuFactory;
import kz.omar.ui.commons.VerticalMenuFactory;
import kz.omar.ui.pages.home.MainPageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-25
 * @project learn_kazakh
 */
@SpringUI(path = LearnKazakhUI.NAME)
@Title("Learn Kazakh")
@Theme("kazakh")
public class LearnKazakhUI extends UI {
    
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
    
    @Autowired
    private FooterFactory footerFactory;
    
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout rootLayout = new VerticalLayout();
        //        rootLayout.setSizeFull();
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
        //        rootLayout.setExpandRatio(horMenuPanel, 1);
        
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
        rootLayout.setComponentAlignment(bottomPanel, Alignment.TOP_CENTER);
        rootLayout.setExpandRatio(bottomPanel, 5);
        
        Panel footerPanel = new Panel();
        footerPanel.setSizeFull();
        
        footerFactory.createComponent();
        Component footerMenuLayout = footerFactory.getFooterMenuLayout();
        footerPanel.setContent(footerMenuLayout);
        
        rootLayout.addComponent(footerPanel);
        rootLayout.setExpandRatio(footerPanel, 1);
        
        initNavigator();
        
        setContent(rootLayout);
    }
    
    private void initNavigator() {
        LearnKazakhNavigator navigator = new LearnKazakhNavigator(this, changePanel);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(navigator);
        navigator.addProvider(springViewProvider);
        navigator.navigateTo(MainPageFactory.NAME);
    }
    
}
