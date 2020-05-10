package remorse.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import remorse.data.DatabaseHandler;
import remorse.domain.HighScore;

/**
 * Luokka ohjaa HighScoreSceneä.
 */
public class HighScoreSceneController implements Initializable {
    
    private ReMorseUI application;
    private DatabaseHandler dbHandler;
    
    public void setCommons(ReMorseUI application, DatabaseHandler dbHandler) {
        this.application = application;
        this.dbHandler = dbHandler;
    }

    /**
     * Ei tällä hetkellä käytössä.
     * @param url Ei käytössä
     * @param rb Ei käytössä
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private VBox letterPoints;
    
    @FXML
    private VBox letterDates;
    
    @FXML
    private VBox wordPoints;
    
    @FXML
    private VBox wordDates;
    
    @FXML
    private Button clearLettersButton;
    
    @FXML
    private Button clearWordsButton;
    
    @FXML
    private Button returnButton;
    
    @FXML
    public void update() {
        letterPoints.getChildren().clear();
        letterDates.getChildren().clear();
        wordPoints.getChildren().clear();
        wordDates.getChildren().clear();
        ArrayList<HighScore> letterScores = dbHandler.letterHighScores();
        letterScores.forEach(score -> {
            letterPoints.getChildren().add(new Label(score.getPoints()));
            letterDates.getChildren().add(new Label(score.getTime()));
        });
        ArrayList<HighScore> wordScores = dbHandler.wordHighScores();
        wordScores.forEach(score -> {
            wordPoints.getChildren().add(new Label(score.getPoints()));
            wordDates.getChildren().add(new Label(score.getTime()));
        });        
    }
    
    @FXML
    private void clearLettersButtonAction() {
        if (dbHandler.clearLetterScores()) {
            update();
        };
    }
    
    @FXML
    private void clearWordsButtonAction() {
        if (dbHandler.clearWordScores()) {
            update();
        };
    }
    
    @FXML private void returnButtonAction() {
        application.setMainScene();
    }
    
}
