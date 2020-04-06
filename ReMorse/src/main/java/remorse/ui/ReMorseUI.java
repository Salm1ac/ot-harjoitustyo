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
import remorse.domain.MorseParser;

/**
 *
 * @author risto
 */
public class ReMorseUI extends Application {
    
    private Stage stage;
    private Scene mainScene;
    private Scene letterScene;
    
    @Override
    public void init() throws Exception {
        HashMap<Character, String> alphabet = new HashMap<>();
        try(Scanner alphabetScanner = new Scanner(Paths.get("alphabet.txt"), "utf-8")) {
            while(alphabetScanner.hasNextLine()) {
                String line = alphabetScanner.nextLine();
                String[] pair = line.split(" ");
                alphabet.put(pair[0].charAt(0), pair[1]);
            }
        } catch (Exception e) {
            System.out.println("Virhe tiedostoa luettaessa: " + e.getMessage());
        }
        MorseParser parser = new MorseParser(alphabet);
        
        FXMLLoader mainSceneLoader = new FXMLLoader(getClass().getResource("/fxml/MainScene.fxml"));
        Parent mainPane = mainSceneLoader.load();
        MainSceneController mainSceneController = mainSceneLoader.getController();
        mainSceneController.setApplication(this);
        mainScene = new Scene(mainPane);
        
        FXMLLoader letterSceneLoader = new FXMLLoader(getClass().getResource("/fxml/LetterScene.fxml"));
        Parent letterPane = letterSceneLoader.load();
        LetterSceneController letterSceneController = letterSceneLoader.getController();
        letterScene = new Scene(letterPane);
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


    public static void main(String[] args) {
        launch(args);
    }
    
}
