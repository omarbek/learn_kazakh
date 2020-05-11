package kz.omar.utils;

/**
 * @author Omarbek.Dinassil
 * on 2020-05-07
 * @project learn_kazakh
 */
public enum ButtonUtils {
    
    NEXT("Следующая"),
    PREVIOUS("Предыдущая"),
    ANSWER("Ответить"),
    FINISH("Завершить"),
    
    ;
    
    private String item;
    
    ButtonUtils(String item){
        this.item=item;
    }
    
    @Override
    public String toString() {
        return item;
    }

}
