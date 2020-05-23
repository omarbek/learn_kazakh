package kz.omar.utils;

/**
 * @author Omarbek.Dinassil
 * on 2020-05-23
 * @project learn_kazakh
 */
public enum TitleUtils {
    
    SIGN_UP("Зарегистрироваться"),
    NOT_READY("Извините, страница пока не готова"),
    
    ;
    
    private String item;
    
    TitleUtils(String item) {
        this.item = item;
    }
    
    @Override
    public String toString() {
        return item;
    }
    
}
