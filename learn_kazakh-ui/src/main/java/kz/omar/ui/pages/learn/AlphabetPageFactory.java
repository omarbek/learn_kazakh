package kz.omar.ui.pages.learn;

import com.vaadin.event.MouseEvents;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import kz.omar.ui.pages.common.AbstractPageFactory;
import kz.omar.ui.start.LearnKazakhUI;
import kz.omar.utils.PageUtils;

/**
 * @author Omarbek.Dinassil
 * on 2020-05-03
 * @project learn_kazakh
 */
@SpringView(name = AlphabetPageFactory.NAME, ui = LearnKazakhUI.class)
public class AlphabetPageFactory extends AbstractPageFactory {
    
    static final String NAME = PageUtils.Constants.ALPHABET_VALUE;
    
    public AlphabetPageFactory() {
        super(NAME);
    }
    
    @Override
    protected void addLayout() {
        setMargin(true);
        
        Panel letterPanel = new Panel();
        letterPanel.setContent(new Label("а"));
        addComponent(letterPanel);
    
        final Audio a = new Audio(null);
        a.setAutoplay(false);
        a.setShowControls(false);
        a.setHtmlContentAllowed(false);
        a.setAltText("Can't play media");
        a.setStyleName("invisible");
        a.setSources(new ExternalResource("/learn_kazakh/VAADIN/sounds/alm.m4a"));
        addComponent(a);
        
        Panel imgPanel = new Panel();
        Embedded image = new Embedded();
        image.setSource(new ThemeResource("../../images/alphabet/alma.jpg"));
        image.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent event) {
                a.play();
            }
        });
        imgPanel.setContent(image);
        addComponent(imgPanel);
    }
}
