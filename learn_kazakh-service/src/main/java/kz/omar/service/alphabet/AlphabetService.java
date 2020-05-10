package kz.omar.service.alphabet;

import kz.omar.model.entity.Alphabet;
import kz.omar.model.entity.AlphabetMedia;

import java.util.List;

/**
 * @author Omarbek.Dinassil
 * on 2020-05-07
 * @project learn_kazakh
 */
public interface AlphabetService {
    
    List<Alphabet> getAlphabet();
    
    List<AlphabetMedia> getMediaByLetter(Integer alphabetId);
    
    List<AlphabetMedia> getRandomAlphabetMediaWithThis(Integer alphabetMediaId);
    
    List<AlphabetMedia> getAlphabetMedia();
    
}
