/**
 * @author Omarbek.Dinassil
 * on 2020-05-03
 * @project learn_kazakh
 */
public enum NotNullUtils {
    
    NAME_MUST_NOT_BE_NULL("Имя не должно быть пустым"),
    
    ;
    
    private final String item;
    
    NotNullUtils(String item) {
        this.item = item;
    }
    
    @Override
    public String toString() {
        return item;
    }
}
