package kz.omar.ui.commons;


import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-25
 * @project learn_kazakh
 */
@org.springframework.stereotype.Component
public class LearnKazakhLogoLayoutFactory implements UIComponentBuilder {
    
    private LogoLayout logoLayout;
    
    public LearnKazakhLogoLayoutFactory() {
        logoLayout = new LogoLayout();
    }
    
    private class LogoLayout extends VerticalLayout {//Builder pattern
        
        public LogoLayout init() {
            return this;
        }
        
        public LogoLayout layout() {
            final Label selection = new Label("-");
            addComponent(selection);
            
            // Define a common menu command for all the menu items.
            MenuBar.Command mycommand = new MenuBar.Command() {
                public void menuSelected(MenuBar.MenuItem selectedItem) {
                    selection.setValue("Ordered a " +
                            selectedItem.getText() +
                            " from menu.");
                }
            };
            
            MenuBar barmenu = new MenuBar();
            addComponent(barmenu);
            
            MenuBar.MenuItem drinks = barmenu.addItem("Beverages", null, null);
            
            // Submenu item with a sub-submenu
            MenuBar.MenuItem hots = drinks.addItem("Hot", null, null);
            hots.addItem("Tea",
                    new ThemeResource("../../themes/univercity/img/book.png"), mycommand);
            hots.addItem("Coffee",
                    new ThemeResource("icons/coffee-16px.png"), mycommand);
            
            // Another submenu item with a sub-submenu
            MenuBar.MenuItem colds = drinks.addItem("Cold", null, null);
            colds.addItem("Milk", null, mycommand);
            colds.addItem("Weissbier", null, mycommand);
            
            // Another top-level item
            MenuBar.MenuItem snacks = barmenu.addItem("Snacks", null, null);
            snacks.addItem("Weisswurst", null, mycommand);
            snacks.addItem("Bratwurst", null, mycommand);
            snacks.addItem("Currywurst", null, mycommand);
            
            // Yet another top-level item
            MenuBar.MenuItem servs = barmenu.addItem("Services", null, null);
            servs.addItem("Car Service", null, mycommand);
            
            // A sub-menu item after a separator
            drinks.addSeparator();
            drinks.addItem("Quit Drinking", null, null);
            
            return this;
        }
        
    }
    
    public void createComponent() {
        logoLayout.init().layout();
    }
    
}
