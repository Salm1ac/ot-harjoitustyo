/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remorse.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author risto
 */
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
