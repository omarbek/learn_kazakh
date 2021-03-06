package kz.omar.navigator;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.SingleComponentContainer;
import com.vaadin.ui.UI;
import kz.omar.ui.pages.common.NotReadyPageFactory;

/**
 * @author Omarbek.Dinassil
 * on 2020-04-25
 * @project learn_kazakh
 */
public class LearnKazakhNavigator extends Navigator {
    
    public LearnKazakhNavigator(UI ui, SingleComponentContainer container) {
        super(ui, container);
    }
    
    private static LearnKazakhNavigator getNavigator() {
        UI ui = UI.getCurrent();
        Navigator navigator = ui.getNavigator();
        return (LearnKazakhNavigator) navigator;
    }
    
    public static void navigate(String selectedItemPath) {
        try {
            String path;
            if (selectedItemPath == null) {
                path = null;
            } else {
                path = selectedItemPath.toLowerCase().replaceAll("\\s+", "");
            }
            LearnKazakhNavigator navigator = LearnKazakhNavigator.getNavigator();
            navigator.navigateTo(path);
        } catch (Exception ignored) {
        }
    }
    
    @Override
    public void navigateTo(String viewName) {
        if (viewName == null) {
            super.navigateTo(NotReadyPageFactory.NAME);
        } else {
            try {
                super.navigateTo(viewName);
            } catch (Exception e) {
                super.navigateTo(NotReadyPageFactory.NAME);
            }
        }
    }
    
}
