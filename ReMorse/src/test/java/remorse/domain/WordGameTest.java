package remorse.domain;

import remorse.data.AlphabetLoader;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import remorse.data.DatabaseHandler;

public class WordGameTest {
    
    WordGame wordGame;
    
    @Before
    public void setUp() {
        AlphabetLoader alphabetLoader = new AlphabetLoader();
        HashMap<Character, String> alphabet = alphabetLoader.loadAlphabet("/alphabets/alphabet.txt");        
        Parser parser = new Parser(alphabet);
        wordGame = new WordGame(parser, new DatabaseHandler("jdbc:sqlite:testi.db"));
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
    public void nextWordReturnsCorrectWord() {
        String[] pair = wordGame.nextWord();
        assertEquals("koita", pair[0]);
    }
    
    @Test
    public void nextWordReturnsCorrectMorseWord() {
        String[] pair = wordGame.nextWord();
        assertEquals("-.- --- .. - .-", pair[1]);
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
}
