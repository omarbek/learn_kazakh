package kz.omar.utils;

/**
 * @author Omarbek.Dinassil
 * on 2020-05-03
 * @project learn_kazakh
 */
public enum PageUtils {
    
    ALPHABET(Constants.ALPHABET_VALUE),
    FIND_WORD(Constants.FIND_WORD_VALUE),
    
    ;
    
    PageUtils(String item) {
    }
    
    public static class Constants {
        public static final String ALPHABET_VALUE = "алфавит";
        public static final String FIND_WORD_VALUE = "найдислово";
    }
    
}
