package kz.omar.ui.pages.home;

import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import kz.omar.ui.pages.common.AbstractPageFactory;
import kz.omar.ui.start.LearnKazakhUI;
import kz.omar.utils.PageUtils;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-26
 * @project learn_kazakh
 */
@SpringView(name = BooksPageFactory.NAME, ui = LearnKazakhUI.class)
public class BooksPageFactory extends AbstractPageFactory {//todo
    
    static final String NAME = PageUtils.Constants.BOOKS_VALUE;
    
    BooksPageFactory() {
        super(NAME);
    }
    
    @Override
    public void addLayout() {
        setMargin(true);
        
        Panel panel = new Panel();
        panel.setContent(new Label("Книги"));
        
        addComponent(panel);
    }
    
}
