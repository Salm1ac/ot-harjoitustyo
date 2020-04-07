/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remorse.domain;

import java.util.Random;

/**
 *
 * @author risto
 */
public class LetterGame {    
    
    private final MorseParser parser;
    private final Random random;
    private final String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", 
                            "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", 
                            "t", "v", "w", "x", "y", "z", "å", "ä", "ö"};
    private int points = 0;
    private int errors = 0;
    private boolean isOngoing = false;

    public LetterGame(MorseParser parser, long seed) {
        this.parser = parser;
        this.random = new Random(seed);
    }
    
    public LetterGame(MorseParser parser) {
        this.parser = parser;
        this.random = new Random();
    }
    
    public String[] nextLetter() {
        int i = random.nextInt(letters.length);
        String letter = letters[i];
        String parsed = parser.parseString(letter);
        return new String[] {letter, parsed};
    }
    
    public boolean checkGuess(String guess, String correct) {
        if (guess.equalsIgnoreCase(correct)) {
            points++;
            return true;
        } else {
            errors++;
            if (errors > 2) isOngoing = false;
            return false;
        }
    }    
    
    public void newGame() {
        errors = 0;
        points = 0;
        isOngoing = true;
    }

    public int getPoints() {
        return points;
    }

    public int getErrors() {
        return errors;
    }

    public boolean isIsOngoing() {
        return isOngoing;
    }
    
    
    
    
    
     
}
