package kz.omar.ui.commons;

import com.vaadin.data.Property;
import com.vaadin.ui.*;
import com.vaadin.ui.Component;
import kz.omar.navigator.LearnKazakhNavigator;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-25
 * @project learn_kazakh
 */
@org.springframework.stereotype.Component
public class LearnKazakhMenuFactory implements UIComponentBuilder {
    
    private LearnKazakhMenu learnKazakhMenu;
    private String menu;
    private String path;
    
    public LearnKazakhMenuFactory(){
        menu="sss";
        learnKazakhMenu=new LearnKazakhMenu();
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public LearnKazakhMenu getLearnKazakhMenu() {
        return learnKazakhMenu;
    }
    
    public void setMenu(String menu) {
        this.menu = menu;
    }
    
    private class LearnKazakhMenu extends VerticalLayout implements Property.ValueChangeListener{
    
        private Tree mainMenu;
        private String exit = "Logout";
    
        public LearnKazakhMenu init() {
            mainMenu = new Tree();
            mainMenu.addValueChangeListener(this);
            return this;
        }
    
        public LearnKazakhMenu layout() {
            setWidth("100%");
            setHeightUndefined();
    
            List<String> list=new ArrayList<>();
            if(menu.equals("sss")){
                list.add("main");
                list.add("qwe");
            }else{
                list.add("asd");
                list.add(exit);
            }
        
            for(String s:list){
                mainMenu.addItem(s);
                mainMenu.expandItem(s);
                mainMenu.setChildrenAllowed(s,false);
            }
        
            addComponent(mainMenu);
        
            return this;
        }
    
        public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
            String selectedItemPath = (String) valueChangeEvent.getProperty().getValue();
        
            if (selectedItemPath == null) {
                return;
            }
        
            if (selectedItemPath.equals(exit)) {
                SecurityContextHolder.clearContext();
                UI.getCurrent().getPage().setLocation("/learn_kazakh/login");
            }
        
            String path = selectedItemPath.toLowerCase().replaceAll("\\s+", "");
            LearnKazakhNavigator.navigate(path);
        }
    }
    
    public void createComponent() {
        learnKazakhMenu.removeAllComponents();
        learnKazakhMenu.init().layout();
        LearnKazakhNavigator.navigate(path);
    }
    
}
