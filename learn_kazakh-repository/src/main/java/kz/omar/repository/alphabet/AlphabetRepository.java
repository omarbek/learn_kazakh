package kz.omar.repository.alphabet;

import kz.omar.model.entity.Alphabet;
import kz.omar.model.entity.AlphabetMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Omarbek.Dinassil
 * on 2020-05-07
 * @project learn_kazakh
 */
@Repository
public interface AlphabetRepository extends JpaRepository<Alphabet,Integer> {
    
    @Query("select a from Alphabet a")
    List<Alphabet> getAlphabet();
    
}
