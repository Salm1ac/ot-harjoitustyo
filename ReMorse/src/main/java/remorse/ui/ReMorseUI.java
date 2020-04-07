/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remorse.ui;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import remorse.domain.AlphabetLoader;
import remorse.domain.MorseParser;

/**
 *
 * @author risto
 */
public class ReMorseUI extends Application {
    
    private Stage stage;
    private Scene mainScene;
    private Scene letterScene;
    private Scene wordScene;
    
    @Override
    public void init() throws Exception {
        AlphabetLoader alphabetLoader = new AlphabetLoader();
        HashMap<Character, String> alphabet = alphabetLoader.loadAlphabet("alphabet.txt");        
        MorseParser parser = new MorseParser(alphabet);
        
        FXMLLoader mainSceneLoader = new FXMLLoader(getClass().getResource("/fxml/MainScene.fxml"));
        Parent mainPane = mainSceneLoader.load();
        MainSceneController mainSceneController = mainSceneLoader.getController();
        mainSceneController.setApplication(this);
        mainScene = new Scene(mainPane);
        
        FXMLLoader letterSceneLoader = new FXMLLoader(getClass().getResource("/fxml/LetterScene.fxml"));
        Parent letterPane = letterSceneLoader.load();
        LetterSceneController letterSceneController = letterSceneLoader.getController();
        letterSceneController.setApplication(this);
        letterSceneController.createLetterGame(parser);
        letterScene = new Scene(letterPane);
        
        FXMLLoader wordSceneLoader = new FXMLLoader(getClass().getResource("/fxml/WordScene.fxml"));
        Parent wordPane = wordSceneLoader.load();
        WordSceneController wordSceneController = wordSceneLoader.getController();
        wordSceneController.setApplication(this);
        wordSceneController.createWordGame(parser);
        wordScene = new Scene(wordPane);
    }
    
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("ReMorse");
        setMainScene();
        stage.show();
    }
    
    public void setMainScene() {
        stage.setScene(mainScene);
    }
    
    public void setLetterScene() {
        stage.setScene(letterScene);
    }
    
    public void setWordScene() {
        stage.setScene(wordScene);
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
