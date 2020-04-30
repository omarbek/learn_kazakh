package kz.omar.ui.commons;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.Label;
import kz.omar.ui.start.UIComponentBuilder;
import org.springframework.stereotype.Component;

import javax.swing.plaf.synth.SynthLookAndFeel;
import java.awt.*;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-29
 * @project learn_kazakh
 */
@Component
public class FooterFactory implements UIComponentBuilder {
    
    private FooterMenuLayout footerMenuLayout;
    
    private class FooterMenuLayout extends VerticalLayout {
    
        public FooterMenuLayout init() {
            return this;
        }
    
        public FooterMenuLayout load() {
            return this;
        }
    
        public void layout() {
            removeAllComponents();
            addComponent(new Label("qwe"));
            addComponent(new Label("qwe"));
            addComponent(new Label("qwe"));
            addComponent(new Label("qwe"));
            addComponent(new Label("qwe"));
            addComponent(new Label("qwe"));
            addComponent(new Label("qwe"));
        }
    }
    
    @Override
    public void createComponent() {
        footerMenuLayout.init().load().layout();
    }
    
    public FooterFactory() {
        footerMenuLayout=new FooterMenuLayout();
    }
    
    public FooterMenuLayout getFooterMenuLayout() {
        return footerMenuLayout;
    }
}
