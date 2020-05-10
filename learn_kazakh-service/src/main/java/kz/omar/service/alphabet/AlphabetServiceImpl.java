package kz.omar.service.alphabet;

import kz.omar.model.entity.Alphabet;
import kz.omar.model.entity.AlphabetMedia;
import kz.omar.repository.alphabet.AlphabetMediaRepository;
import kz.omar.repository.alphabet.AlphabetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Omarbek.Dinassil
 * on 2020-05-07
 * @project learn_kazakh
 */
@Service
public class AlphabetServiceImpl implements AlphabetService {
    
    @Autowired
    private AlphabetRepository alphabetRepository;
    
    @Autowired
    private AlphabetMediaRepository alphabetMediaRepository;
    
    @Override
    public List<Alphabet> getAlphabet() {
        return alphabetRepository.getAlphabet();
    }
    
    @Override
    public List<AlphabetMedia> getMediaByLetter(Integer alphabetId) {
        return alphabetMediaRepository.getMediaByLetter(alphabetId);
    }
    
    @Override
    public List<AlphabetMedia> getRandomAlphabetMediaWithThis(Integer alphabetMediaId) {
        AlphabetMedia alphabetMedia = alphabetMediaRepository.findOne(alphabetMediaId);
        List<AlphabetMedia> list = alphabetMediaRepository.getRandomAlphabetMediaWithoutThis(alphabetMediaId);
        list.add(alphabetMedia);
        Collections.shuffle(list);
        return list;
    }
    
    @Override
    public List<AlphabetMedia> getAlphabetMedia() {
        List<AlphabetMedia> ret = new ArrayList<>();
        List<Integer> alphabetMediaIds = alphabetMediaRepository.getAlphabetMediaIds();
        for (Integer id: alphabetMediaIds) {
            AlphabetMedia alphabetMedia = alphabetMediaRepository.findOne(id);
            ret.add(alphabetMedia);
        }
        return ret;
    }
    
}
