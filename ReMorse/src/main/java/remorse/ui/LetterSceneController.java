package remorse.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import remorse.domain.LetterGame;
import remorse.domain.Parser;
import remorse.domain.MorseSequence;

/**
 * Luokka ohjaa LetterSceneä.
 */
public class LetterSceneController implements Initializable {
    
    private ReMorseUI application;
    private LetterGame letterGame;
    private String correct;
    
    private MorseSequence sequence;
    private Beeper beeper;
    private AnimationTimer timer;
    
    /**
     * Metodi luo bittijonon, piipittäjän ja ajastimen.
     * Ajastin lukee bittijonosta bitin ja asettaa sen perusteella 
     * valon ja äänen päälle tai pois.
     * @param url Ei käytössä
     * @param rb Ei käytössä
     * @see javafx.animation.AnimationTimer
     * @see remorse.domain.MorseSequence#nextBit() 
     * @see remorse.ui.Beeper
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sequence = new MorseSequence();
        beeper = new Beeper();
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (sequence.nextBit()) {
                    morseLight.setFill(Color.GOLD);
                    if (!beeper.isOn()) {
                        beeper.turnOn();
                    }
                } else {
                    morseLight.setFill(Color.SIENNA);
                    if (beeper.isOn()) {
                        beeper.turnOff();
                    }
                }
            }
        };
    }   
    
    /**
     * Metodi yhdistää ohjaimen sovellukseen.
     * @param application Ohjainta käyttävä sovellus
     * @see remorse.ui.ReMorseUI
     */
    public void setApplication(ReMorseUI application) {
        this.application = application;
    }
    
    /**
     * Metodi luo ohjaimelle kirjainpelin.
     * @param parser Käytettävä parseri
     * @see remorse.domain.LetterGame
     * @see remorse.domain.Parser
     */
    public void createLetterGame(Parser parser) {
        this.letterGame = new LetterGame(parser);
    }
    
    @FXML
    private Circle morseLight;
    
    @FXML
    private Button returnButton;
    
    /**
     * Metodi keskeyttää pelin ja palauttaa käyttäjän päävalikkoon.
     * @see javafx.animation.AnimationTimer
     * @see remorse.domain.LetterGame#stopGame() 
     * @see remorse.ui.Beeper
     */
    @FXML
    private void returnButtonAction() {
        timer.stop();
        beeper.turnOff();
        letterGame.stopGame();
        application.setMainScene();
        
    } 
    
    @FXML
    private Label pointCounter;
    
    @FXML
    private Label errorCounter;
        
    @FXML
    private Label morseContainer;
    
    @FXML
    private TextField guessInput;
    
    @FXML
    private Button newGameButton;
    
    /**
     * Metodi aloittaa uuden pelin.
     * @see javafx.animation.AnimationTimer
     * @see remorse.domain.LetterGame
     * @see remorse.domain.MorseSequence#createSequence(java.lang.String) 
     * @see remorse.ui.Beeper
     */
    @FXML
    private void newGameButtonAction() {
        timer.stop();
        beeper.turnOff();
        letterGame.newGame();
        pointCounter.setText("Pisteitä: " + String.valueOf(letterGame.getPoints()));
        errorCounter.setText("Virheitä: " + String.valueOf(letterGame.getErrors()));
        guessInput.setVisible(true);
        String[] nextLetter = letterGame.nextPrompt();
        correct = nextLetter[0];
        sequence.createSequence(nextLetter[1]);
        timer.start();
    }      
    
    /**
     * Metodi tarkistaa käyttäjän tekemän arvauksen ja päivittää näkymän.
     * @param event Ei käytössä
     * @see javafx.animation.AnimationTimer
     * @see remorse.domain.LetterGame
     * @see remorse.domain.MorseSequence#createSequence(java.lang.String) 
     * @see remorse.ui.Beeper
     */
    @FXML
    private void guessInputAction(ActionEvent event) {
        if (!letterGame.isOngoing()) {
            return;
        }
        timer.stop();
        beeper.turnOff();
        letterGame.checkGuess(guessInput.getText(), correct);
        pointCounter.setText("Pisteitä: " + String.valueOf(letterGame.getPoints()));
        errorCounter.setText("Virheitä: " + String.valueOf(letterGame.getErrors()));
        String[] nextLetter = letterGame.nextPrompt();
        correct = nextLetter[0];
        sequence.createSequence(nextLetter[1]);
        guessInput.clear();
        if (!letterGame.isOngoing()) {
            guessInput.setVisible(false);
            return;
        }
        timer.start();
    }
     
}
