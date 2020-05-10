package remorse.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * Luokka ohjaa päävalikkoa. 
 */
public class MainSceneController implements Initializable {
    
    private ReMorseUI application;
    
    /**
     * Metodi yhdistää ohjaimen sovellukseen.
     * @param application Ohjainta käyttävä sovellus
     * @see remorse.ui.ReMorseUI
     */
    public void setApplication(ReMorseUI application) {
        this.application = application;
    }
    
    @FXML
    private Button exitButton;
    
    /**
     * Metodi sulkee ohjelman.
     */
    @FXML
    private void exitButtonAction() {
        Platform.exit();
    }
    
    @FXML
    private Button letterButton;
    
    /**
     * Metodi siirtää käyttäjän kirjainpeliin.
     * @param event Ei käytössä
     * @see remorse.ui.LetterSceneController
     */
    @FXML
    private void handleLetter(ActionEvent event) {
        this.application.setLetterScene();
    }
    
    @FXML
    private Button wordButton;
    
    /**
     * Metodi siirtää käyttäjän sanapeliin.
     * @param event Ei käytössä
     * @see remorse.ui.WordSceneController
     */
    @FXML
    private void handleWord(ActionEvent event) {
        this.application.setWordScene();
    }
    
    @FXML
    private Button highScoreButton;
        
    /**
     * Metodi siirtää käyttäjän pistetaulukoihin.
     * @param event Ei käytössä
     * @see remorse.ui.HighScoreSceneController
     */
    @FXML
    private void handleHighScore(ActionEvent event) {
        this.application.setHighScoreScene();
    }
       
    @FXML
    private Button settingsButton;
        
    /**
     * Metodi siirtää käyttäjän asetuksiin.
     * @param event Ei käytössä
     * @see remorse.ui.HighScoreSceneController
     */
    @FXML
    private void handleSettings(ActionEvent event) {
        this.application.setSettingsScene();
    }

    
    /**
     * Metodi ei tee tällä hetkellä mitään alustusta.
     * @param url Ei käytössä
     * @param rb Ei käytössä
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
