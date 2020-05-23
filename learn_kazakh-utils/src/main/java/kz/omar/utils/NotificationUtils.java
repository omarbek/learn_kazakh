package kz.omar.utils;

/**
 * @author Omarbek.Dinassil
 * on 2020-05-23
 * @project learn_kazakh
 */
public enum NotificationUtils {
    
    ERROR("Ошибка!"),
    PASSWORDS_DO_NOT_MATCH("Пароли не совпадают!"),
    LOGIN_FAIL("Ошибка входа! Попробуйте еще раз!"),
    RIGHT("Правильно!"),
    WRONG("Не правильно!"),
    NO_ACCESS("У вас нет доступа к этой странице!"),
    
    ;
    
    private String item;
    
    NotificationUtils(String item) {
        this.item = item;
    }
    
    @Override
    public String toString() {
        return item;
    }
    
}
