package kz.omar.ui.pages.test;

import com.vaadin.event.MouseEvents;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import kz.omar.model.entity.AlphabetMedia;
import kz.omar.service.alphabet.AlphabetService;
import kz.omar.service.test.TestService;
import kz.omar.service.user.UserService;
import kz.omar.ui.pages.common.AbstractPageFactory;
import kz.omar.ui.start.LearnKazakhUI;
import kz.omar.utils.ButtonUtils;
import kz.omar.utils.NotificationUtils;
import kz.omar.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Omarbek.Dinassil
 * on 2020-07-02
 * @project learn_kazakh
 */
@SpringView(name = FindWordTestPageFactory.NAME, ui = LearnKazakhUI.class)
public class FindWordTestPageFactory extends AbstractPageFactory {
    
    static final String NAME = PageUtils.Constants.FIND_WORD_TEST_VALUE;
    
    @Autowired
    private AlphabetService alphabetService;
    
    @Autowired
    private TestService testService;
    
    @Autowired
    private UserService userService;
    
    private List<AlphabetMedia> wordAlphabetList;
    private List<AlphabetMedia> randomAlphabetList;
    private List<Embedded> images;
    
    private Button nextButton;
    private Button answerButton;
    private Button previousButton;
    private Button finishButton;
    
    private int page = 0;
    private String wordName;
    private String answeredName;
    private int count = 0;
    
    public FindWordTestPageFactory() {
        super(NAME);
        wordAlphabetList = new ArrayList<>();
        randomAlphabetList = new ArrayList<>();
        images = new ArrayList<>();
    }
    
    @Override
    protected void addLayout() {
        setHeightUndefined();
        wordAlphabetList = alphabetService.getAlphabetMedia();
        
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
        
        answerButton = new Button(ButtonUtils.ANSWER.toString());
        answerButton.setWidthUndefined();
        answerButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        answerButton.addClickListener(event -> {
            if (answeredName.equals(wordName)) {
                Notification.show(NotificationUtils.RIGHT.toString(), Notification.Type.HUMANIZED_MESSAGE);
                count++;
            } else {
                Notification.show(NotificationUtils.WRONG.toString(), Notification.Type.ERROR_MESSAGE);
            }
            answerButton.setEnabled(false);
            finishButton.setEnabled(true);
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
        
        finishButton = new Button(ButtonUtils.FINISH.toString());
        finishButton.setWidthUndefined();
        finishButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        finishButton.setVisible(false);
        finishButton.addClickListener(event -> {
            Notification.show("Тренировка завершена! Количество набранных очков: " + count);
            //todo navigate to empty page
            testService.save(userService.getCurrentUser(), 1, count);
        });
        
        init(gridLayout, page);
        
        HorizontalLayout buttonsHL = new HorizontalLayout(answerButton, nextButton, finishButton);
        buttonsHL.setWidthUndefined();
        buttonsHL.setSpacing(true);
        buttonsHL.setMargin(true);
        
        gridLayout.addComponent(buttonsHL, 0, 2, 2, 2);
        gridLayout.setComponentAlignment(buttonsHL, Alignment.MIDDLE_CENTER);
        
        panel.setContent(gridLayout);
        addComponent(panel);
    }
    
    private void init(GridLayout gridLayout, int page) {
        answerButton.setEnabled(true);
        finishButton.setEnabled(false);
        if (page == 0) {
            previousButton.setEnabled(false);
        } else {
            previousButton.setEnabled(true);
        }
        
        if (page == wordAlphabetList.size() - 1) {
            finishButton.setVisible(true);
            nextButton.setEnabled(false);
        } else {
            nextButton.setEnabled(true);
        }
        
        AlphabetMedia alphabetMedia = wordAlphabetList.get(page);
        wordName = alphabetMedia.getName();
        
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
                    "/learn_kazakh/VAADIN/sounds/alphabet/" + audioSource, media.getName());
        }
    }
    
    private void addImagesToGrid(GridLayout gridLayout, int column, String imageSource, String audioSource,
                                 String name) {
        Embedded image = new Embedded();
        image.setWidth("350px");
        image.addStyleName("audio-image");
        image.setSource(new ThemeResource(imageSource));
        image.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent event) {
                answeredName = name;
                for (Embedded embedded: images) {
                    embedded.removeStyleName("image-clicked");
                }
                image.addStyleName("image-clicked");
            }
        });
        images.add(image);
        gridLayout.removeComponent(column, 1);
        gridLayout.addComponent(image, column, 1);
        gridLayout.setComponentAlignment(image, Alignment.MIDDLE_CENTER);
    }
    
}
