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
import remorse.domain.MorseParser;
import remorse.domain.MorseSequence;

public class LetterSceneController implements Initializable {
    
    private ReMorseUI application;
    private LetterGame letterGame;
    private String correct;
    
    private MorseSequence sequence = new MorseSequence();
    private Beeper beeper = new Beeper();
    private AnimationTimer timer = new AnimationTimer() {
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
    
    public void setApplication(ReMorseUI application) {
        this.application = application;
    }
    
    public void createLetterGame(MorseParser parser) {
        this.letterGame = new LetterGame(parser);
    }
    
    @FXML
    private Circle morseLight;
    
    @FXML
    private Button returnButton;
    
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
    
    @FXML
    private void newGameButtonAction() {
        timer.stop();
        beeper.turnOff();
        letterGame.newGame();
        pointCounter.setText("Pisteitä: " + String.valueOf(letterGame.getPoints()));
        errorCounter.setText("Virheitä: " + String.valueOf(letterGame.getErrors()));
        guessInput.setVisible(true);
        String[] nextLetter = letterGame.nextLetter();
        correct = nextLetter[0];
        sequence.createSequence(nextLetter[1]);
        timer.start();
        // morseContainer.setText(nextLetter[1]);
    }      
    
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
        String[] nextLetter = letterGame.nextLetter();
        correct = nextLetter[0];
        sequence.createSequence(nextLetter[1]);
        // morseContainer.setText(nextLetter[1]);
        guessInput.clear();
        if (!letterGame.isOngoing()) {
            guessInput.setVisible(false);
            return;
        }
        timer.start();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
