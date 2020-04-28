
package remorse.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class MainSceneController implements Initializable {
    
    private ReMorseUI application;
    
    public void setApplication(ReMorseUI application) {
        this.application = application;
    }
    
    @FXML
    private Button exitButton;
    
    @FXML
    private void exitButtonAction() {
        Platform.exit();
    }
    
    @FXML
    private Button letterButton;
    
    @FXML
    private void handleLetter(ActionEvent event) {
        this.application.setLetterScene();
    }
    
    @FXML
    private Button wordButton;
    
    @FXML
    private void handleWord(ActionEvent event) {
        this.application.setWordScene();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
