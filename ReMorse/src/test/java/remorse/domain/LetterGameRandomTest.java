package remorse.domain;

import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LetterGameRandomTest {
    
    LetterGame letterGame;
    
    @Before
    public void setUp() {
        AlphabetLoader alphabetLoader = new AlphabetLoader();
        HashMap<Character, String> alphabet = alphabetLoader.loadAlphabet("/alphabets/alphabet.txt");        
        MorseParser parser = new MorseParser(alphabet);
        letterGame = new LetterGame(parser);
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
    
}
