package kz.omar.utils;

/**
 * @author Omarbek.Dinassil
 * on 2020-05-03
 * @project learn_kazakh
 */
public enum PageUtils {
    
    ALPHABET(Constants.ALPHABET_VALUE),
    FIND_WORD(Constants.FIND_WORD_VALUE),
    MY_PROFILE(Constants.MY_PROFILE_VALUE),
    MY_PROGRESS(Constants.MY_PROGRESS_VALUE),
    MY_VOCABULARY(Constants.MY_VOCABULARY_VALUE),
    LOGOUT(Constants.LOGOUT_VALUE),
    NOT_READY(Constants.NOT_READY_VALUE),
    BOOKS(Constants.BOOKS_VALUE),
    MAIN(Constants.MAIN_VALUE),
    MUSIC(Constants.MUSIC_VALUE),
    FIND_WORD_TEST(Constants.FIND_WORD_TEST_VALUE),
    
    ;
    
    PageUtils(String item) {
    }
    
    public static class Constants {
        public static final String ALPHABET_VALUE = "алфавит";
        public static final String FIND_WORD_VALUE = "найдислово";
        public static final String MY_PROFILE_VALUE = "Мой профиль";
        public static final String MY_PROGRESS_VALUE = "Мой прогресс";
        public static final String MY_VOCABULARY_VALUE = "Мой словарь";
        public static final String LOGOUT_VALUE = "Выйти";
        public static final String NOT_READY_VALUE = "неготово";
        public static final String BOOKS_VALUE = "книги";
        public static final String MAIN_VALUE = "главная";
        public static final String MUSIC_VALUE = "музыка";
        public static final String FIND_WORD_TEST_VALUE = "тестнайдислово";
    }
    
}
