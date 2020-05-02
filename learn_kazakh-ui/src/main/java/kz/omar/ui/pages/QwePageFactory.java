package kz.omar.ui.pages;

import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import kz.omar.ui.pages.common.AbstractPageFactory;
import kz.omar.ui.start.LearnKazakhUI;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-26
 * @project learn_kazakh
 */
@SpringView(name = QwePageFactory.NAME, ui = LearnKazakhUI.class)
public class QwePageFactory extends AbstractPageFactory {
    
    static final String NAME = "книги";
    
    QwePageFactory(){
        super(NAME);
    }
    
    @Override
    public void addLayout() {
        setMargin(true);
        
        Panel panel = new Panel();
        panel.setContent(new Label("qwe"));
        
        addComponent(panel);
    }
    
}
