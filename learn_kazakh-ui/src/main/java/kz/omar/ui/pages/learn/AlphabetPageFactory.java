package kz.omar.ui.pages.learn;

import com.vaadin.event.MouseEvents;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import kz.omar.model.entity.Alphabet;
import kz.omar.model.entity.AlphabetMedia;
import kz.omar.service.alphabet.AlphabetService;
import kz.omar.ui.pages.common.AbstractPageFactory;
import kz.omar.ui.start.LearnKazakhUI;
import kz.omar.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Omarbek.Dinassil
 * on 2020-05-03
 * @project learn_kazakh
 */
@SpringView(name = AlphabetPageFactory.NAME, ui = LearnKazakhUI.class)
public class AlphabetPageFactory extends AbstractPageFactory {
    
    static final String NAME = PageUtils.Constants.ALPHABET_VALUE;
    
    @Autowired
    private AlphabetService alphabetService;
    
    private List<Alphabet> alphabetList;
    private List<AlphabetMedia> alphabetMediaList;
    
    public AlphabetPageFactory() {
        super(NAME);
        alphabetList = new ArrayList<>();
        alphabetMediaList = new ArrayList<>();
    }
    
    @Override
    protected void addLayout() {
        setMargin(true);
        alphabetList = alphabetService.getAlphabet();
        
        Panel panel = new Panel();
        
        GridLayout gridLayout = new GridLayout(3, 3);
        gridLayout.setHeightUndefined();
        gridLayout.setSpacing(true);
        
        int page = 0;
        init(gridLayout, page);
        
        Button nextButton = new Button("next");
        nextButton.setWidthUndefined();
        nextButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                init(gridLayout, page + 1);
            }
        });
        gridLayout.addComponent(nextButton, 1, 2, 2, 2);
        gridLayout.setComponentAlignment(nextButton, Alignment.MIDDLE_CENTER);
        
        panel.setContent(gridLayout);
        addComponent(panel);
    }
    
    private void init(GridLayout gridLayout, int page) {
        Alphabet alphabet = alphabetList.get(page);
        String letter = alphabet.getLetter();
        
        Label letterLabel = new Label("<h1>" + letter + "</h1>", ContentMode.HTML);
        letterLabel.setWidthUndefined();
        letterLabel.addStyleName("footer");
        gridLayout.removeComponent(1, 0);
        gridLayout.addComponent(letterLabel, 1, 0);
        gridLayout.setComponentAlignment(letterLabel, Alignment.MIDDLE_CENTER);
        
        alphabetMediaList = alphabetService.getMediaByLetter(alphabet.getId());
        int count = 0;
        for (AlphabetMedia media: alphabetMediaList) {
            String imageSource = media.getImageSource();
            String audioSource = media.getAudioSource();
            addImagesToGrid(gridLayout, count++, "../../images/alphabet/" + imageSource,
                    "/learn_kazakh/VAADIN/sounds/alphabet/" + audioSource);
        }
    }
    
    private void addImagesToGrid(GridLayout gridLayout, int column, String imageSource, String audioSource) {
        Audio audio = new Audio(null);
        audio.setAutoplay(false);
        audio.setShowControls(false);
        audio.setHtmlContentAllowed(false);
        audio.setAltText("Can't play media");
        audio.setStyleName("invisible");
        audio.setSources(new ExternalResource(audioSource));
        addComponent(audio);
        
        Embedded image = new Embedded();
        image.setWidth("350px");
        image.addStyleName("audio-hover");
        image.setSource(new ThemeResource(imageSource));
        image.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent event) {
                audio.play();
            }
        });
        gridLayout.removeComponent(column, 1);
        gridLayout.addComponent(image, column, 1);
    }
}
