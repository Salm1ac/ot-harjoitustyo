/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remorse.domain;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author risto
 */
public class LetterGameTest {
    
    LetterGame letterGame;
    
    @Before
    public void setUp() {
        AlphabetLoader alphabetLoader = new AlphabetLoader();
        HashMap<Character, String> alphabet = alphabetLoader.loadAlphabet("/alphabets/alphabet.txt");        
        MorseParser parser = new MorseParser(alphabet);
        letterGame = new LetterGame(parser, 42);
    }
    
    @Test
    public void gameIsInitiallyNotOngoing() {
        assertEquals(false, letterGame.isIsOngoing());
    }
    
    @Test
    public void newGameIsOngoing() {
        letterGame.newGame();
        assertEquals(true, letterGame.isIsOngoing());
    }
    
    @Test
    public void nextLetterReturnsCorrectLetter() {
        String[] pair = letterGame.nextLetter();
        assertEquals("x", pair[0]);
    }
    
    @Test
    public void nextLetterReturnsCorrectMorseLetter() {
        String[] pair = letterGame.nextLetter();
        assertEquals("-..-", pair[1]);
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
        assertEquals(false, letterGame.isIsOngoing());
    }
    
}
