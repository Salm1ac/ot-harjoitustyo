/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remorse.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import remorse.domain.MorseParser;
import remorse.domain.WordGame;

/**
 * FXML Controller class
 *
 * @author risto
 */
public class WordSceneController implements Initializable {

    private ReMorseUI application;
    private WordGame wordGame;
    private String correct;
    
    public void setApplication(ReMorseUI application) {
        this.application = application;
    }
    
    public void createWordGame(MorseParser parser) {
        this.wordGame = new WordGame(parser);
    }
    
    @FXML
    private Button returnButton;
    
    @FXML
    private void returnButtonAction() {
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
        wordGame.newGame();
        pointCounter.setText("Pisteitä: " + String.valueOf(wordGame.getPoints()));
        errorCounter.setText("Virheitä: " + String.valueOf(wordGame.getErrors()));
        guessInput.setVisible(true);
        String[] nextWord = wordGame.nextWord();
        correct = nextWord[0];
        morseContainer.setText(nextWord[1]);
    }      
    
    @FXML
    private void guessInputAction(ActionEvent event) {
        if (!wordGame.isIsOngoing()) {
            return;
        }
        wordGame.checkGuess(guessInput.getText(), correct);
        pointCounter.setText("Pisteitä: " + String.valueOf(wordGame.getPoints()));
        errorCounter.setText("Virheitä: " + String.valueOf(wordGame.getErrors()));
        String[] nextWord = wordGame.nextWord();
        correct = nextWord[0];
        morseContainer.setText(nextWord[1]);
        guessInput.clear();
        if (!wordGame.isIsOngoing()) {
            guessInput.setVisible(false);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
