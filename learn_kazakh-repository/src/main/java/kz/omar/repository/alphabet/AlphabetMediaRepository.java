package kz.omar.repository.alphabet;

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
public interface AlphabetMediaRepository extends JpaRepository<AlphabetMedia, Integer> {
    
    @Query("select am from AlphabetMedia am where am.alphabet.id = :alphabetId")
    List<AlphabetMedia> getMediaByLetter(@Param("alphabetId") Integer alphabetId);
    
    @Query(value = "select * from alphabet_media where id != :alphabetMediaId order by rand() limit 2",
            nativeQuery = true)
    List<AlphabetMedia> getRandomAlphabetMediaWithoutThis(@Param("alphabetMediaId") Integer alphabetMediaId);
    
    @Query(value =
            "select (select id from alphabet_media where alphabet_id = alphabet.id order by rand() limit 1) as id" +
                    " from alphabet", nativeQuery = true)
    List<Integer> getAlphabetMediaIds();
    
}
