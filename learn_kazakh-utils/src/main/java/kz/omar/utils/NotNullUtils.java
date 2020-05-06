package kz.omar.utils;

/**
 * @author Omarbek.Dinassil
 * on 2020-05-03
 * @project learn_kazakh
 */
public enum NotNullUtils {
    
    NAME_MUST_NOT_BE_NULL(Constants.NAME_MUST_NOT_BE_NULL_VALUE),
    LETTER_MUST_NOT_BE_NULL(Constants.LETTER_MUST_NOT_BE_NULL_VALUE),
    
    ;
    
    NotNullUtils(String item) {
    }
    
    public static class Constants {
        public static final String NAME_MUST_NOT_BE_NULL_VALUE = "Имя не должно быть пустым";
        public static final String LETTER_MUST_NOT_BE_NULL_VALUE = "Буква не должна быть пустым";
    }
    
}
