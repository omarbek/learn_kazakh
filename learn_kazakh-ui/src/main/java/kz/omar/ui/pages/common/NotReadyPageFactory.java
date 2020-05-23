package kz.omar.ui.pages.common;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import kz.omar.ui.start.LearnKazakhUI;
import kz.omar.utils.PageUtils;
import kz.omar.utils.TitleUtils;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-26
 * @project learn_kazakh
 */
@SpringView(name = NotReadyPageFactory.NAME, ui = LearnKazakhUI.class)
public class NotReadyPageFactory extends VerticalLayout implements View {
    
    public static final String NAME = PageUtils.Constants.NOT_READY_VALUE;
    
    private void addLayout() {
        setMargin(true);
        
        Panel panel = new Panel();
        panel.setContent(new Label(TitleUtils.NOT_READY.toString()));
        
        addComponent(panel);
    }
    
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        removeAllComponents();
        addLayout();
    }
    
}
