package remorse.ui;

import java.util.HashMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import remorse.domain.AlphabetLoader;
import remorse.domain.Parser;

/**
 * Luokka toimii pääsovelluksena.
 */
public class ReMorseUI extends Application {
    
    private Stage stage;
    private Scene mainScene;
    private Scene letterScene;
    private Scene wordScene;
    
    /**
     * Metodi alustaa sovelluksen, aakkoston ja käyttöliittymän.
     * @see remorse.domain.AlphabetLoader#loadAlphabet(java.lang.String) 
     * @see remorse.domain.Parser
     * @see remorse.ui.MainSceneController
     * @see remorse.ui.LetterSceneController
     * @see remorse.ui.WordSceneController
     * @throws Exception 
     */
    @Override
    public void init() throws Exception {
        AlphabetLoader alphabetLoader = new AlphabetLoader();
        HashMap<Character, String> alphabet = alphabetLoader.loadAlphabet("/alphabets/alphabet.txt");        
        Parser parser = new Parser(alphabet);
        
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
    
    /**
     * Metodi asettaa päävalikon näkyviin.
     * @param stage Ikkuna, jossa näytetään
     */
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("ReMorse");
        setMainScene();
        stage.show();
    }
    
    /**
     * Metodi siirtää käyttäjän päävalikkoon.
     * @see remorse.ui.MainSceneController
     */
    public void setMainScene() {
        stage.setScene(mainScene);
    }
    
    /**
     * Metodi siirtää käyttäjän kirjainpeliin.
     * @see remorse.ui.LetterSceneController
     */
    public void setLetterScene() {
        stage.setScene(letterScene);
    }
    
    /**
     * Metodi siirtää käyttäjän sanapeliin.
     * @see remorse.ui.WordSceneController
     */
    public void setWordScene() {
        stage.setScene(wordScene);
    }

    /**
     * Metodi käynnistää sovelluksen.
     * @param args Käynnistysargumentit
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
