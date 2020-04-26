package kz.omar.navigator;

import com.google.gwt.thirdparty.guava.common.base.Strings;
import com.vaadin.navigator.Navigator;
import com.vaadin.ui.SingleComponentContainer;
import com.vaadin.ui.UI;

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
    
    public static void navigate(String path) {
        try {
            LearnKazakhNavigator.getNavigator().navigateTo(path);
        } catch (Exception e) {
            return;
        }
    }
    
    @Override
    public void navigateTo(String viewName) {
        super.navigateTo(Strings.nullToEmpty(viewName));
    }
    
}
