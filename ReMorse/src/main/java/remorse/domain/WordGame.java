package remorse.domain;

import java.util.Random;

public class WordGame {
    
    private final MorseParser parser;
    private final Random random;
    private final String[] words = {
        "kenttä", "potku", "kettu", "hissi", "katko", "takoi",
        "rikos", "pirinä", "pentti", "heheh", "pihdit",
        "koita", "tulos", "pommi", "passi", "lahti"}; 
    // tulevaisuudessa Kotuksen sanalista tiedostosta
    // sanamäärä mahdollisesti kymmeniä tuhansia
    private int points = 0;
    private int errors = 0;
    private boolean ongoing = false;

    public WordGame(MorseParser parser, long seed) {
        this.parser = parser;
        this.random = new Random(seed);
    }
    
    public WordGame(MorseParser parser) {
        this.parser = parser;
        this.random = new Random();
    }
    
    public String[] nextWord() {
        int i = random.nextInt(words.length);
        String letter = words[i];
        String parsed = parser.parseString(letter);
        return new String[] {letter, parsed};
    }
    
    public boolean checkGuess(String guess, String correct) {
        if (guess.equalsIgnoreCase(correct)) {
            points++;
            return true;
        } else {
            errors++;
            if (errors > 2) {
                ongoing = false;
            }
            return false;
        }
    }    
    
    public void newGame() {
        errors = 0;
        points = 0;
        ongoing = true;
    }
    
    public void stopGame() {
        errors = 0;
        points = 0;
        ongoing = false;
    }

    public int getPoints() {
        return points;
    }

    public int getErrors() {
        return errors;
    }

    public boolean isOngoing() {
        return ongoing;
    }
    
}
