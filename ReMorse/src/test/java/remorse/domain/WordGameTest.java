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
public class WordGameTest {
    
    WordGame wordGame;
    
    @Before
    public void setUp() {
        AlphabetLoader alphabetLoader = new AlphabetLoader();
        HashMap<Character, String> alphabet = alphabetLoader.loadAlphabet("alphabet.txt");        
        MorseParser parser = new MorseParser(alphabet);
        wordGame = new WordGame(parser, 42);
    }
    
    @Test
    public void gameIsInitiallyNotOngoing() {
        assertEquals(false, wordGame.isIsOngoing());
    }
    
    @Test
    public void newGameIsOngoing() {
        wordGame.newGame();
        assertEquals(true, wordGame.isIsOngoing());
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
        assertEquals(false, wordGame.isIsOngoing());
    }
}
