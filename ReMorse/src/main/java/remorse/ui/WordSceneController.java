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
import remorse.domain.MorseParser;
import remorse.domain.MorseSequence;
import remorse.domain.WordGame;

public class WordSceneController implements Initializable {

    private ReMorseUI application;
    private WordGame wordGame;
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
    
    public void createWordGame(MorseParser parser) {
        this.wordGame = new WordGame(parser);
    }
    
    @FXML
    private Circle morseLight;
    @FXML
    private Button returnButton;
    
    @FXML
    private void returnButtonAction() {
        timer.stop();
        beeper.turnOff();
        wordGame.stopGame();
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
        wordGame.newGame();
        pointCounter.setText("Pisteitä: " + String.valueOf(wordGame.getPoints()));
        errorCounter.setText("Virheitä: " + String.valueOf(wordGame.getErrors()));
        guessInput.setVisible(true);
        String[] nextWord = wordGame.nextPrompt();
        correct = nextWord[0];
        sequence.createSequence(nextWord[1]);
        timer.start();
        //morseContainer.setText(nextWord[1]);
    }      
    
    @FXML
    private void guessInputAction(ActionEvent event) {
        if (!wordGame.isOngoing()) {
            return;
        }
        timer.stop();
        beeper.turnOff();
        wordGame.checkGuess(guessInput.getText(), correct);
        pointCounter.setText("Pisteitä: " + String.valueOf(wordGame.getPoints()));
        errorCounter.setText("Virheitä: " + String.valueOf(wordGame.getErrors()));
        String[] nextWord = wordGame.nextPrompt();
        correct = nextWord[0];
        sequence.createSequence(nextWord[1]);
        //morseContainer.setText(nextWord[1]);
        guessInput.clear();
        if (!wordGame.isOngoing()) {
            guessInput.setVisible(false);
            return;
        }
        timer.start();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
