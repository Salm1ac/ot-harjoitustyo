package remorse.ui;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import remorse.data.AlphabetLoader;
import remorse.data.DatabaseHandler;
import remorse.domain.LetterGame;
import remorse.domain.MorseSequence;
import remorse.domain.Parser;
import remorse.domain.WordGame;

/**
 * Luokka toimii pääsovelluksena.
 */
public class ReMorseUI extends Application {
    
    private Stage stage;
    private Scene mainScene;
    private Scene letterScene;
    private Scene wordScene;
    private Scene highScoreScene;
    private Scene settingsScene;
    
    private HighScoreSceneController highScoreSceneController;
    
    /**
     * Metodi alustaa sovelluksen, aakkoston, tietokantayhteyden ja käyttöliittymän.
     * @see remorse.data.AlphabetLoader#loadAlphabet(java.lang.String) 
     * @see remorse.data.DatabaseHandler
     * @see remorse.domain.Parser
     * @see remorse.ui.MainSceneController
     * @see remorse.ui.LetterSceneController
     * @see remorse.ui.WordSceneController
     * @see remorse.ui.HighScoreSceneController
     * @see remorse.ui.SettingsSceneController
     */
    @Override
    public void init() {
        Properties properties = new Properties();
        String database = "remorse.db";
        int volume = 100;
        int note = 69;
        int timeUnit = 16;
        int maxErrors = 2;
        try {
            properties.load(new FileInputStream("./config.properties"));
            database = properties.getProperty("database", "remorse.db");
            volume = Integer.valueOf(properties.getProperty("volume", "100"));
            note = Integer.valueOf(properties.getProperty("note", "69"));
            timeUnit = Integer.valueOf(properties.getProperty("timeUnit", "16"));
            maxErrors = Integer.valueOf(properties.getProperty("maxErrors", "2"));
        } catch (Exception e) {
            System.out.println("Virhe asetuksissa, käytetään oletusasetuksia.");
        }
        
        DatabaseHandler dbHandler = new DatabaseHandler("jdbc:sqlite:./" + database);
        AlphabetLoader alphabetLoader = new AlphabetLoader();
        HashMap<Character, String> alphabet = alphabetLoader.loadAlphabet("/alphabets/alphabet.txt");           
        Parser parser = new Parser(alphabet);
        MorseSequence seq = new MorseSequence();
        Beeper beeper = new Beeper();
        LetterGame letterGame = new LetterGame(parser, dbHandler);
        WordGame wordGame = new WordGame(parser, dbHandler);
        
        try {
            FXMLLoader mainSceneLoader = new FXMLLoader(getClass().getResource("/fxml/MainScene.fxml"));
            Parent mainPane = mainSceneLoader.load();
            MainSceneController mainSceneController = mainSceneLoader.getController();
            mainSceneController.setApplication(this);
            mainScene = new Scene(mainPane);

            FXMLLoader letterSceneLoader = new FXMLLoader(getClass().getResource("/fxml/LetterScene.fxml"));
            Parent letterPane = letterSceneLoader.load();
            LetterSceneController letterSceneController = letterSceneLoader.getController();
            letterSceneController.setCommons(this, seq, beeper, letterGame);
            letterScene = new Scene(letterPane);

            FXMLLoader wordSceneLoader = new FXMLLoader(getClass().getResource("/fxml/WordScene.fxml"));
            Parent wordPane = wordSceneLoader.load();
            WordSceneController wordSceneController = wordSceneLoader.getController();
            wordSceneController.setCommons(this, seq, beeper, wordGame);
            wordScene = new Scene(wordPane);

            FXMLLoader highScoreSceneLoader = new FXMLLoader(getClass().getResource("/fxml/HighScoreScene.fxml"));
            Parent highScorePane = highScoreSceneLoader.load();
            highScoreSceneController = highScoreSceneLoader.getController();
            highScoreSceneController.setCommons(this, dbHandler);
            highScoreScene = new Scene(highScorePane);

            FXMLLoader settingsSceneLoader = new FXMLLoader(getClass().getResource("/fxml/SettingsScene.fxml"));
            Parent settingsPane = settingsSceneLoader.load();
            SettingsSceneController settingsSceneController = settingsSceneLoader.getController();
            settingsSceneController.setCommons(this, seq, beeper, letterGame, wordGame);
            settingsSceneController.setDefaults(volume, note, timeUnit, maxErrors);
            settingsScene = new Scene(settingsPane);
        } catch (Exception e) {
            System.out.println("Virhe alustuksessa.");
            Platform.exit();
        }
        
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
     * Metodi siirtää käyttäjän pistetaulukoihin.
     * Samalla pistetaulukot päivitetään.
     * @see remorse.ui.HighScoreSceneController
     */
    public void setHighScoreScene() {
        highScoreSceneController.update();
        stage.setScene(highScoreScene);
    }
    
    /**
     * Metodi siirtää käyttäjän asetuksiin.
     * @see remorse.ui.SettingsSceneController
     */
    public void setSettingsScene() {
        stage.setScene(settingsScene);
    }
    
    /**
     * Metodi käynnistää sovelluksen.
     * @param args Käynnistysargumentit
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
