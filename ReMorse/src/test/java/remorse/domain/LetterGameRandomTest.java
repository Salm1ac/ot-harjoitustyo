package remorse.domain;

import java.util.ArrayList;
import remorse.data.AlphabetLoader;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import remorse.data.DatabaseHandler;

public class LetterGameRandomTest {
    
    LetterGame letterGame;
    DatabaseHandler dbHandler;
    
    @Before
    public void setUp() {
        AlphabetLoader alphabetLoader = new AlphabetLoader();
        HashMap<Character, String> alphabet = alphabetLoader.loadAlphabet("/alphabets/alphabet.txt");        
        Parser parser = new Parser(alphabet);
        dbHandler = new DatabaseHandler("jdbc:sqlite:testi2.db");
        letterGame = new LetterGame(parser, dbHandler);
        dbHandler.clearLetterScores();
    }
    
    @Test
    public void gameIsInitiallyNotOngoing() {
        assertEquals(false, letterGame.isOngoing());
    }
    
    @Test
    public void newGameIsOngoing() {
        letterGame.newGame();
        assertEquals(true, letterGame.isOngoing());
    }
    
    @Test
    public void noInitialPointsInNewGame() {
        letterGame.newGame();
        assertEquals(0, letterGame.getPoints());
    }
    
    @Test
    public void noInitialErrorsInNewGame() {
        letterGame.newGame();
        assertEquals(0, letterGame.getErrors());
    }
    
    @Test
    public void correctGuessGivesPoints() {
        letterGame.newGame();
        letterGame.checkGuess("i", "i");
        assertEquals(1, letterGame.getPoints());
    }
    
    @Test
    public void correctGuessGivesNoErrors() {
        letterGame.newGame();
        letterGame.checkGuess("i", "i");
        assertEquals(0, letterGame.getErrors());
    }
    
    @Test
    public void incorrectGuessGivesNoPoints() {
        letterGame.newGame();
        letterGame.checkGuess("i", "k");
        assertEquals(0, letterGame.getPoints());
    }
    
    @Test
    public void incorrectGuessGivesErrors() {
        letterGame.newGame();
        letterGame.checkGuess("i", "k");
        assertEquals(1, letterGame.getErrors());
    }
    
    @Test
    public void checkGuessIgnoresCase() {
        letterGame.newGame();
        assertEquals(true, letterGame.checkGuess("i", "I"));
    }
    
    @Test
    public void threeErrorsStopsGame() {
        letterGame.newGame();
        letterGame.checkGuess("i", "k");
        letterGame.checkGuess("i", "k");
        letterGame.checkGuess("i", "k");
        assertEquals(false, letterGame.isOngoing());
    }
    
    @Test
    public void noPointsAfterStop() {
        letterGame.newGame();
        letterGame.checkGuess("i", "i");
        letterGame.stopGame();
        assertEquals(0, letterGame.getPoints());
    }
    
    @Test
    public void noErrorsAfterStop() {
        letterGame.newGame();
        letterGame.checkGuess("i", "k");
        letterGame.stopGame();
        assertEquals(0, letterGame.getErrors());
    }
    
    @Test
    public void notOngoingAfterStop() {
        letterGame.newGame();
        letterGame.stopGame();
        assertEquals(false, letterGame.isOngoing());
    }
    
    @Test
    public void maxFiveScoresInHighscores() {
        dbHandler.clearLetterScores();
        for (int i = 0; i < 10; i++) {
            letterGame.newGame();
            letterGame.checkGuess("i", "i");
            letterGame.checkGuess("i", "k");
            letterGame.checkGuess("i", "k");
            letterGame.checkGuess("i", "k");
        }
        assertEquals(5, dbHandler.letterHighScores().size());
    }
    
    @Test
    public void emptyScoresAfterClear() {
        dbHandler.clearLetterScores();
        for (int i = 0; i < 10; i++) {
            letterGame.newGame();
            letterGame.checkGuess("i", "i");
            letterGame.checkGuess("i", "k");
            letterGame.checkGuess("i", "k");
            letterGame.checkGuess("i", "k");
        }
        dbHandler.clearLetterScores();
        assertEquals(0, dbHandler.letterHighScores().size());
    }
    
    @Test
    public void scoreSavedAtGameOver() {
        dbHandler.clearLetterScores();
        letterGame.newGame();
        letterGame.checkGuess("i", "k");
        letterGame.checkGuess("i", "k");
        letterGame.checkGuess("i", "k");
        assertEquals(false, dbHandler.letterHighScores().isEmpty());
        assertEquals("0", dbHandler.letterHighScores().get(0).getPoints());
    }
    
    @Test
    public void nonZeroScoreSavedAtNewGame() {
        dbHandler.clearLetterScores();
        letterGame.newGame();
        letterGame.checkGuess("i", "i");
        letterGame.newGame();
        assertEquals(false, dbHandler.letterHighScores().isEmpty());
        assertEquals("1", dbHandler.letterHighScores().get(0).getPoints());
    }
    
    @Test
    public void zeroScoreNotSavedAtNewGame() {
        dbHandler.clearLetterScores();
        letterGame.newGame();
        letterGame.checkGuess("i", "k");
        letterGame.newGame();
        assertEquals(true, dbHandler.letterHighScores().isEmpty());
    }
    
    @Test
    public void nonZeroScoreNotSavedAtNewGameIfNotOngoing() {
        dbHandler.clearLetterScores();
        letterGame.newGame();
        letterGame.checkGuess("i", "i");
        letterGame.stopGame();
        dbHandler.clearLetterScores();
        letterGame.newGame();
        assertEquals(true, dbHandler.letterHighScores().isEmpty());
    }
    
    @Test
    public void zeroScoreNotSavedAtNewGameIfNotOngoing() {
        dbHandler.clearLetterScores();
        letterGame.newGame();
        letterGame.checkGuess("i", "k");
        letterGame.stopGame();
        dbHandler.clearLetterScores();
        letterGame.newGame();
        assertEquals(true, dbHandler.letterHighScores().isEmpty());
    }
    
    
    @Test
    public void nonZeroScoreSavedAtStopGame() {
        dbHandler.clearLetterScores();
        letterGame.newGame();
        letterGame.checkGuess("i", "i");
        letterGame.stopGame();
        assertEquals(false, dbHandler.letterHighScores().isEmpty());
        assertEquals("1", dbHandler.letterHighScores().get(0).getPoints());
    }
    
    @Test
    public void zeroScoreNotSavedAtStopGame() {
        dbHandler.clearLetterScores();
        letterGame.newGame();
        letterGame.checkGuess("i", "k");
        letterGame.stopGame();
        assertEquals(true, dbHandler.letterHighScores().isEmpty());
    }
    
    @Test
    public void scoreNotSavedAgainAfterGameOverByNewGame() {
        dbHandler.clearLetterScores();
        letterGame.newGame();
        letterGame.checkGuess("i", "i");
        letterGame.checkGuess("i", "k");
        letterGame.checkGuess("i", "k");
        letterGame.checkGuess("i", "k");
        letterGame.newGame();
        assertEquals(1, dbHandler.letterHighScores().size());
    }
    
    @Test
    public void scoreNotSavedAgainAfterGameOverByStopGame() {
        dbHandler.clearLetterScores();
        letterGame.newGame();
        letterGame.checkGuess("i", "i");
        letterGame.checkGuess("i", "k");
        letterGame.checkGuess("i", "k");
        letterGame.checkGuess("i", "k");
        letterGame.stopGame();
        assertEquals(1, dbHandler.letterHighScores().size());
    }
    
    @Test
    public void changingMaxErrorsToZeroEndsGameAtFirstError() {
        letterGame.setMaxErrors(0);
        letterGame.newGame();
        letterGame.checkGuess("i", "k");
        assertEquals(false, letterGame.isOngoing());
    }
    
    @Test
    public void highScoresHaveTimestamps() {
        dbHandler.clearLetterScores();
        for (int i = 0; i < 10; i++) {
            letterGame.newGame();
            letterGame.checkGuess("i", "i");
            letterGame.checkGuess("i", "k");
            letterGame.checkGuess("i", "k");
            letterGame.checkGuess("i", "k");
        }
        ArrayList<HighScore> highscores = dbHandler.letterHighScores();
        for (int j = 0; j < highscores.size(); j++) {
            assertEquals(false, highscores.get(j).getTime().equals(""));
        }
        
    }
    
}
