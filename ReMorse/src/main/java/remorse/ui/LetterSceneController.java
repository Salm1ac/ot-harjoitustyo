/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remorse.ui;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import remorse.domain.MorseParser;

/**
 * FXML Controller class
 *
 * @author risto
 */
public class LetterSceneController implements Initializable {
    
    private ReMorseUI application;
    private MorseParser parser;
    private Random random = new Random();
    private String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", 
                            "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", 
                            "t", "v", "w", "x", "y", "z", "å", "ä", "ö"};
    
    public void setApplication(ReMorseUI application) {
        this.application = application;
    }
    
    public void setParser(MorseParser parser) {
        this.parser = parser;
    }
    
    @FXML
    private Button returnButton;
    
    @FXML
    private void returnButtonAction(ActionEvent event) {
        application.setMainScene();
    } 
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
