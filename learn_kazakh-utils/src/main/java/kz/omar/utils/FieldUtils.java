package kz.omar.utils;

/**
 * @author Omarbek.Dinassil
 * on 2020-05-23
 * @project learn_kazakh
 */
public enum FieldUtils {
    
    USER_NAME("Имя пользователя"),
    PASSWORD("Пароль"),
    PASSWORD_AGAIN("Повторите пароль"),
    ROLE("Роль"),
    
    ;
    
    private String item;
    
    FieldUtils(String item){
        this.item=item;
    }
    
    @Override
    public String toString() {
        return item;
    }
    
}
