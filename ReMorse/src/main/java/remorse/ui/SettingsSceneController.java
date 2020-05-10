package remorse.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import remorse.domain.LetterGame;
import remorse.domain.MorseSequence;
import remorse.domain.WordGame;

/**
 * Luokka ohjaa SettingsSceneä.
 */
public class SettingsSceneController implements Initializable {
    
    private ReMorseUI application;    
    private MorseSequence sequence;
    private Beeper beeper;
    private LetterGame letterGame;
    private WordGame wordGame;
    
    private int defaultVolume = 100;
    private int defaultNote = 69;
    private int defaultTimeUnit = 16;
    private int defaultMaxErrors = 2;

    /**
     * Metodi ei tällä hetkellä käytössä.
     * @param url Ei käytössä
     * @param rb Ei käytössä
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private Slider volumeSlider;
    
    @FXML
    private Slider noteSlider;
    
    @FXML
    private Slider timeUnitSlider;
    
    @FXML
    private Slider  maxErrorsSlider;
    
    /**
     * Metodi yhdistää ohjaimen sovellukseen. Samalla ohjaimelle annetaan
     * yhteinen bittijono, piipittäjä ja pelit. Lisäksi liukusäätimet 
     * asetetaan kuuntelemaan muutoksia.     *
     * @param application Ohjainta käyttävä sovellus
     * @param seq Sovelluksen käyttämä bittijono
     * @param beeper Sovelluksen käyttämä piipittäjä
     * @param letterGame Sovelluksen käyttämä kirjainpeli
     * @param wordGame Sovelluksen käyttämä sanapeli
     * @see remorse.domain.LetterGame
     * @see remorse.domain.MorseSequence
     * @see remorse.domain.WordGame
     * @see remorse.ui.ReMorseUI
     * @see remorse.ui.Beeper
     */
    public void setCommons(ReMorseUI application, MorseSequence seq, Beeper beeper,
                                LetterGame letterGame, WordGame wordGame) {
        this.application = application;
        this.sequence = seq;
        timeUnitSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.sequence.setTimeUnit(newValue.intValue());
        });
        this.beeper = beeper;
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.beeper.setVolume(newValue.intValue());
        });
        noteSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.beeper.setNote(newValue.intValue());
        });
        this.letterGame = letterGame;
        this.wordGame  = wordGame;
        maxErrorsSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.letterGame.setMaxErrors(newValue.intValue());
            this.wordGame.setMaxErrors(newValue.intValue());
        });
    }
    
    /**
     * Metodi asettaa oletusasetukset.
     * @param volume Oletusäänenvoimakkuus, 0-127
     * @param note Oletusnuotti, 50-100
     * @param timeUnit Oletusaikayksikkö, 1-60
     * @param maxErrors Oletusvirheraja, 0-20
     */
    public void setDefaults(int volume, int note, int timeUnit, int maxErrors) {
        defaultVolume = volume;
        defaultNote = note;
        defaultTimeUnit = timeUnit;
        defaultMaxErrors = maxErrors;
        clearButtonAction();
    }
    
    @FXML
    private Button returnButton;
    
    /**
     * Metodi palauttaa käyttäjän päävalikkoon.
     */
    @FXML
    private void returnButtonAction() {
        this.application.setMainScene();
    }
    
    @FXML
    private Button clearButton;
    
    /**
     * Metodi palauttaa sovelluksen oletusasetuksiin.
     */
    @FXML
    private void clearButtonAction() {
        volumeSlider.adjustValue(defaultVolume);
        noteSlider.adjustValue(defaultNote);
        timeUnitSlider.adjustValue(defaultTimeUnit);
        maxErrorsSlider.adjustValue(defaultMaxErrors);
    }
    
}
