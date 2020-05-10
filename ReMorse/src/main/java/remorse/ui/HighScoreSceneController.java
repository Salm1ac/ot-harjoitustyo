package remorse.ui;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Luokka ohjaa HighScoreSceneä.
 */
public class HighScoreSceneController implements Initializable {
    
    private ReMorseUI application;
    
    public void setApplication(ReMorseUI application) {
        this.application = application;
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
    public void update() throws Exception {
        clearLettersButtonAction();
        clearWordsButtonAction();
        ResultSet pointScoreSet = null;
        while (pointScoreSet.next()) {
            Integer points = pointScoreSet.getInt("points");
            Timestamp time = pointScoreSet.getTimestamp("time");
            letterPoints.getChildren().add(new Label(points.toString()));
            letterDates.getChildren().add(new Label(time.toString()));
        }
        ResultSet wordScoreSet = null;
        while (wordScoreSet.next()) {
            Integer points = wordScoreSet.getInt("points");
            Timestamp time = wordScoreSet.getTimestamp("time");
            wordPoints.getChildren().add(new Label(points.toString()));
            wordDates.getChildren().add(new Label(time.toString()));
        }
    }
    
    @FXML
    private void clearLettersButtonAction() {
        letterPoints.getChildren().clear();
        letterDates.getChildren().clear();
    }
    
    @FXML
    private void clearWordsButtonAction() {
        wordPoints.getChildren().clear();
        wordDates.getChildren().clear();
    }
    
    @FXML private void returnButtonAction() {
        application.setMainScene();
    }
    
}
