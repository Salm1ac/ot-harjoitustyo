package remorse.domain;

import java.util.ArrayList;
import remorse.data.AlphabetLoader;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import remorse.data.DatabaseHandler;

public class WordGameRandomTest {
    
    WordGame wordGame;
    DatabaseHandler dbHandler;
    
    @Before
    public void setUp() {
        AlphabetLoader alphabetLoader = new AlphabetLoader();
        HashMap<Character, String> alphabet = alphabetLoader.loadAlphabet("/alphabets/alphabet.txt");        
        Parser parser = new Parser(alphabet);
        dbHandler = new DatabaseHandler("jdbc:sqlite:testi2.db");
        wordGame = new WordGame(parser, dbHandler);
        dbHandler.clearLetterScores();
    }
    
    @Test
    public void gameIsInitiallyNotOngoing() {
        assertEquals(false, wordGame.isOngoing());
    }
    
    @Test
    public void newGameIsOngoing() {
        wordGame.newGame();
        assertEquals(true, wordGame.isOngoing());
    }
        
    @Test
    public void noInitialPointsInNewGame() {
        wordGame.newGame();
        assertEquals(0, wordGame.getPoints());
    }
    
    @Test
    public void noInitialErrorsInNewGame() {
        wordGame.newGame();
        assertEquals(0, wordGame.getErrors());
    }
    
    @Test
    public void correctGuessGivesPoints() {
        wordGame.newGame();
        wordGame.checkGuess("hissi", "hissi");
        assertEquals(1, wordGame.getPoints());
    }
    
    @Test
    public void correctGuessGivesNoErrors() {
        wordGame.newGame();
        wordGame.checkGuess("hissi", "hissi");
        assertEquals(0, wordGame.getErrors());
    }
    
    @Test
    public void incorrectGuessGivesNoPoints() {
        wordGame.newGame();
        wordGame.checkGuess("hissi", "heheh");
        assertEquals(0, wordGame.getPoints());
    }
    
    @Test
    public void incorrectGuessGivesErrors() {
        wordGame.newGame();
        wordGame.checkGuess("hissi", "heheh");
        assertEquals(1, wordGame.getErrors());
    }
    
    @Test
    public void checkGuessIgnoresCase() {
        wordGame.newGame();
        assertEquals(true, wordGame.checkGuess("hissi", "HISSI"));
    }
    
    @Test
    public void threeErrorsStopsGame() {
        wordGame.newGame();
        wordGame.checkGuess("hissi", "heheh");
        wordGame.checkGuess("hissi", "heheh");
        wordGame.checkGuess("hissi", "heheh");
        assertEquals(false, wordGame.isOngoing());
    }
    
    @Test
    public void noPointsAfterStop() {
        wordGame.newGame();
        wordGame.checkGuess("hissi", "hissi");
        wordGame.stopGame();
        assertEquals(0, wordGame.getPoints());
    }
    
    @Test
    public void noErrorsAfterStop() {
        wordGame.newGame();
        wordGame.checkGuess("hissi", "heheh");
        wordGame.stopGame();
        assertEquals(0, wordGame.getErrors());
    }
    
    @Test
    public void notOngoingAfterStop() {
        wordGame.newGame();
        wordGame.stopGame();
        assertEquals(false, wordGame.isOngoing());
    }
    
    @Test
    public void maxFiveScoresInHighscores() {
        dbHandler.clearWordScores();
        for (int i = 0; i < 10; i++) {
            wordGame.newGame();
            wordGame.checkGuess("i", "i");
            wordGame.checkGuess("i", "k");
            wordGame.checkGuess("i", "k");
            wordGame.checkGuess("i", "k");
        }
        assertEquals(5, dbHandler.wordHighScores().size());
    }

    @Test
    public void emptyScoresAfterClear() {
        dbHandler.clearWordScores();
        for (int i = 0; i < 10; i++) {
            wordGame.newGame();
            wordGame.checkGuess("i", "i");
            wordGame.checkGuess("i", "k");
            wordGame.checkGuess("i", "k");
            wordGame.checkGuess("i", "k");
        }
        dbHandler.clearWordScores();
        assertEquals(0, dbHandler.wordHighScores().size());
    }

    @Test
    public void scoreSavedAtGameOver() {
        dbHandler.clearWordScores();
        wordGame.newGame();
        wordGame.checkGuess("i", "k");
        wordGame.checkGuess("i", "k");
        wordGame.checkGuess("i", "k");
        assertEquals(false, dbHandler.wordHighScores().isEmpty());
        assertEquals("0", dbHandler.wordHighScores().get(0).getPoints());
    }

    @Test
    public void nonZeroScoreSavedAtNewGame() {
        dbHandler.clearWordScores();
        wordGame.newGame();
        wordGame.checkGuess("i", "i");
        wordGame.newGame();
        assertEquals(false, dbHandler.wordHighScores().isEmpty());
        assertEquals("1", dbHandler.wordHighScores().get(0).getPoints());
    }

    @Test
    public void zeroScoreNotSavedAtNewGame() {
        dbHandler.clearWordScores();
        wordGame.newGame();
        wordGame.checkGuess("i", "k");
        wordGame.newGame();
        assertEquals(true, dbHandler.wordHighScores().isEmpty());
    }

    @Test
    public void nonZeroScoreNotSavedAtNewGameIfNotOngoing() {
        dbHandler.clearWordScores();
        wordGame.newGame();
        wordGame.checkGuess("i", "i");
        wordGame.stopGame();
        dbHandler.clearWordScores();
        wordGame.newGame();
        assertEquals(true, dbHandler.wordHighScores().isEmpty());
    }

    @Test
    public void zeroScoreNotSavedAtNewGameIfNotOngoing() {
        dbHandler.clearWordScores();
        wordGame.newGame();
        wordGame.checkGuess("i", "k");
        wordGame.stopGame();
        dbHandler.clearWordScores();
        wordGame.newGame();
        assertEquals(true, dbHandler.wordHighScores().isEmpty());
    }

    @Test
    public void nonZeroScoreSavedAtStopGame() {
        dbHandler.clearWordScores();
        wordGame.newGame();
        wordGame.checkGuess("i", "i");
        wordGame.stopGame();
        assertEquals(false, dbHandler.wordHighScores().isEmpty());
        assertEquals("1", dbHandler.wordHighScores().get(0).getPoints());
    }

    @Test
    public void zeroScoreNotSavedAtStopGame() {
        dbHandler.clearWordScores();
        wordGame.newGame();
        wordGame.checkGuess("i", "k");
        wordGame.stopGame();
        assertEquals(true, dbHandler.letterHighScores().isEmpty());
    }

    @Test
    public void scoreNotSavedAgainAfterGameOverByNewGame() {
        dbHandler.clearWordScores();
        wordGame.newGame();
        wordGame.checkGuess("i", "i");
        wordGame.checkGuess("i", "k");
        wordGame.checkGuess("i", "k");
        wordGame.checkGuess("i", "k");
        wordGame.newGame();
        assertEquals(1, dbHandler.wordHighScores().size());
    }

    @Test
    public void scoreNotSavedAgainAfterGameOverByStopGame() {
        dbHandler.clearWordScores();
        wordGame.newGame();
        wordGame.checkGuess("i", "i");
        wordGame.checkGuess("i", "k");
        wordGame.checkGuess("i", "k");
        wordGame.checkGuess("i", "k");
        wordGame.stopGame();
        assertEquals(1, dbHandler.wordHighScores().size());
    }

    @Test
    public void changingMaxErrorsToZeroEndsGameAtFirstError() {
        wordGame.setMaxErrors(0);
        wordGame.newGame();
        wordGame.checkGuess("i", "k");
        assertEquals(false, wordGame.isOngoing());
    }

    @Test
    public void highScoresHaveTimestamps() {
        dbHandler.clearLetterScores();
        for (int i = 0; i < 10; i++) {
            wordGame.newGame();
            wordGame.checkGuess("i", "i");
            wordGame.checkGuess("i", "k");
            wordGame.checkGuess("i", "k");
            wordGame.checkGuess("i", "k");
        }
        ArrayList<HighScore> highscores = dbHandler.wordHighScores();
        for (int j = 0; j < highscores.size(); j++) {
            assertEquals(false, highscores.get(j).getTime().equals(""));
        }

    }
}
