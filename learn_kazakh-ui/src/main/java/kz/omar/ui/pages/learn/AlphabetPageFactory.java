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
import kz.omar.utils.ButtonUtils;
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
    
    private Button nextButton;
    private Button previousButton;
    private int page = 0;
    
    public AlphabetPageFactory() {
        super(NAME);
        alphabetList = new ArrayList<>();
        alphabetMediaList = new ArrayList<>();
    }
    
    @Override
    protected void addLayout() {
        setHeightUndefined();
        alphabetList = alphabetService.getAlphabet();
        
        Panel panel = new Panel();
        panel.setSizeFull();
        
        GridLayout gridLayout = new GridLayout(3, 5);
        gridLayout.setSizeFull();
        
        nextButton = new Button(ButtonUtils.NEXT.toString());
        nextButton.setWidthUndefined();
        //        nextButton.setHeight("50px");
        nextButton.setIcon(new ThemeResource("../../themes/runo/icons/icons32/arrow-right.png"));
        nextButton.addStyleName("icon-right");
        nextButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                page++;
                init(gridLayout, page);
            }
        });
        
        previousButton = new Button(ButtonUtils.PREVIOUS.toString());
        previousButton.setWidthUndefined();
        previousButton.setIcon(new ThemeResource("../../themes/runo/icons/icons32/arrow-left.png"));
        previousButton.setEnabled(false);
        previousButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                page--;
                init(gridLayout, page);
            }
        });
        
        init(gridLayout, page);
        
        HorizontalLayout buttonsHL = new HorizontalLayout(previousButton, nextButton);
        buttonsHL.setWidthUndefined();
        buttonsHL.setMargin(true);
        buttonsHL.setSpacing(true);
        
        gridLayout.addComponent(buttonsHL, 0, 2, 2, 2);
        gridLayout.setComponentAlignment(buttonsHL, Alignment.MIDDLE_CENTER);
        
        VerticalLayout alphabetVL = new VerticalLayout();
        alphabetVL.setSpacing(true);
        alphabetVL.setMargin(true);
        initAlphabet(alphabetVL, 4, true, gridLayout);
        initAlphabet(alphabetVL, 5, false, gridLayout);
        gridLayout.addComponent(alphabetVL, 0, 4, 2, 4);
        gridLayout.setComponentAlignment(alphabetVL, Alignment.MIDDLE_CENTER);
        
        panel.setContent(gridLayout);
        addComponent(panel);
    }
    
    private void initAlphabet(VerticalLayout alphabetVL, int row, boolean firstPart, GridLayout gridLayout) {
        HorizontalLayout alphabetHL = new HorizontalLayout();
        alphabetHL.setWidthUndefined();
        alphabetHL.setSpacing(true);
        
        int count = 0;
        for (Alphabet alphabet: alphabetList) {
            if ((firstPart && count >= 0 && count < 21)
                    || (!firstPart && count >= 21)) {
                Button letterButton = new Button(alphabet.getLetter());
                //            letterButton.addStyleName(ValoTheme.BUTTON_LINK);
                letterButton.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        page = alphabet.getId() - 1;
                        init(gridLayout, page);
                    }
                });
                alphabetHL.addComponent(letterButton);
                alphabetHL.setComponentAlignment(letterButton, Alignment.MIDDLE_CENTER);
            }
            count++;
        }
        
        alphabetVL.addComponent(alphabetHL);
        alphabetVL.setComponentAlignment(alphabetHL, Alignment.MIDDLE_CENTER);
        
    }
    
    private void init(GridLayout gridLayout, int page) {
        if (page == 0) {
            previousButton.setEnabled(false);
        } else {
            previousButton.setEnabled(true);
        }
        
        if (page == alphabetList.size() - 1) {
            nextButton.setEnabled(false);
        } else {
            nextButton.setEnabled(true);
        }
        
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
        gridLayout.removeComponent(column, 3);
        gridLayout.addComponent(audio, column, 3);
        
        Embedded image = new Embedded();
        image.setWidth("350px");
        image.addStyleName("audio-image");
        image.setSource(new ThemeResource(imageSource));
        image.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent event) {
                audio.play();
            }
        });
        gridLayout.removeComponent(column, 1);
        gridLayout.addComponent(image, column, 1);
        gridLayout.setComponentAlignment(image, Alignment.MIDDLE_CENTER);
    }
}
