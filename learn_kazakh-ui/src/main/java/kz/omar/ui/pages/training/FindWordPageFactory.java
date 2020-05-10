package kz.omar.ui.pages.training;

import com.vaadin.event.MouseEvents;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
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
 * on 2020-05-11
 * @project learn_kazakh
 */
@SpringView(name = FindWordPageFactory.NAME, ui = LearnKazakhUI.class)
public class FindWordPageFactory extends AbstractPageFactory {
    
    static final String NAME = PageUtils.Constants.FIND_WORD_VALUE;
    
    @Autowired
    private AlphabetService alphabetService;
    
    private List<AlphabetMedia> wordAlphabetList;
    private List<AlphabetMedia> randomAlphabetList;
    
    private Button nextButton;
    private Button previousButton;
    private int page = 0;
    
    public FindWordPageFactory(){
        super(NAME);
        wordAlphabetList = new ArrayList<>();
        randomAlphabetList = new ArrayList<>();
    }
    
    @Override
    protected void addLayout() {
        setHeightUndefined();
        wordAlphabetList=alphabetService.getAlphabetMedia();
    
        Panel panel = new Panel();
        panel.setSizeFull();
    
        GridLayout gridLayout = new GridLayout(3, 4);
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
        buttonsHL.setSpacing(true);
    
        gridLayout.addComponent(buttonsHL, 0, 2, 2, 2);
        gridLayout.setComponentAlignment(buttonsHL, Alignment.MIDDLE_CENTER);
    
        panel.setContent(gridLayout);
        addComponent(panel);
    }
    
    private void init(GridLayout gridLayout, int page) {
        if (page == 0) {
            previousButton.setEnabled(false);
        } else {
            previousButton.setEnabled(true);
        }
        
        if (page == wordAlphabetList.size() - 1) {
            nextButton.setEnabled(false);
        } else {
            nextButton.setEnabled(true);
        }
        
        AlphabetMedia alphabetMedia = wordAlphabetList.get(page);
        String wordName = alphabetMedia.getName();
        
        Label letterLabel = new Label("<h1>" + wordName + "</h1>", ContentMode.HTML);
        letterLabel.setWidthUndefined();
        letterLabel.addStyleName("footer");
        gridLayout.removeComponent(1, 0);
        gridLayout.addComponent(letterLabel, 1, 0);
        gridLayout.setComponentAlignment(letterLabel, Alignment.MIDDLE_CENTER);
    
        randomAlphabetList = alphabetService.getRandomAlphabetMediaWithThis(alphabetMedia.getId());
        int count = 0;
        for (AlphabetMedia media: randomAlphabetList) {
            String imageSource = media.getImageSource();
            String audioSource = media.getAudioSource();
            addImagesToGrid(gridLayout, count++, "../../images/alphabet/" + imageSource,
                    "/learn_kazakh/VAADIN/sounds/alphabet/" + audioSource);
        }
    }
    
    private void addImagesToGrid(GridLayout gridLayout, int column, String imageSource, String audioSource) {
        Embedded image = new Embedded();
        image.setWidth("350px");
        image.addStyleName("audio-hover");
        image.setSource(new ThemeResource(imageSource));
        image.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent event) {
                Notification.show("ASD");//todo
            }
        });
        gridLayout.removeComponent(column, 1);
        gridLayout.addComponent(image, column, 1);
    }
    
}
