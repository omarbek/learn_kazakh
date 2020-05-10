package kz.omar.model.entity;

import kz.omar.utils.NotNullUtils;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Omarbek.Dinassil
 * on 2020-05-06
 * @project learn_kazakh
 */
@Entity
@Table(name = "ALPHABET_MEDIA")
@Data
public class AlphabetMedia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name="name")
    private String name;
    
    @Column(name = "image_source")
    private String imageSource;
    
    @Column(name = "audio_source")
    private String audioSource;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alphabet_id")
    @NotNull(message = NotNullUtils.Constants.LETTER_MUST_NOT_BE_NULL_VALUE)
    private Alphabet alphabet;
    
}
