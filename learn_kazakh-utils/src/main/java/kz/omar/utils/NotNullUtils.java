package kz.omar.utils;

/**
 * @author Omarbek.Dinassil
 * on 2020-05-03
 * @project learn_kazakh
 */
public enum NotNullUtils {
    
    NAME_MUST_NOT_BE_NULL(Constants.NAME_MUST_NOT_BE_NULL_VALUE),
    LETTER_MUST_NOT_BE_NULL(Constants.LETTER_MUST_NOT_BE_NULL_VALUE),
    IMAGE_SOURCE_MUST_NOT_BE_NULL(Constants.IMAGE_SOURCE_MUST_NOT_BE_NULL_VALUE),
    AUDIO_SOURCE_MUST_NOT_BE_NULL(Constants.AUDIO_SOURCE_MUST_NOT_BE_NULL_VALUE),
    USERNAME_MUST_NOT_BE_NULL(Constants.USERNAME_MUST_NOT_BE_NULL_VALUE),
    PASSWORD_MUST_NOT_BE_NULL(Constants.PASSWORD_MUST_NOT_BE_NULL_VALUE),
    ROLE_MUST_NOT_BE_NULL(Constants.ROLE_MUST_NOT_BE_NULL_VALUE),
    
    ;
    
    NotNullUtils(String item) {
    }
    
    public static class Constants {
        public static final String NAME_MUST_NOT_BE_NULL_VALUE = "Поле имя не должно быть пустым";
        public static final String LETTER_MUST_NOT_BE_NULL_VALUE = "Поле буква не должно быть пустым";
        public static final String IMAGE_SOURCE_MUST_NOT_BE_NULL_VALUE = "Поле картина не должно быть пустым";
        public static final String AUDIO_SOURCE_MUST_NOT_BE_NULL_VALUE = "Поле аудио не должно быть пустым";
        public static final String USERNAME_MUST_NOT_BE_NULL_VALUE = "Поле имя пользователя не должно быть пустым";
        public static final String PASSWORD_MUST_NOT_BE_NULL_VALUE = "Поле пароль не должно быть пустым";
        public static final String ROLE_MUST_NOT_BE_NULL_VALUE = "Поле роль не должно быть пустым";
    }
    
}
