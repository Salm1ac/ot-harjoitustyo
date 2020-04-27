package remorse.domain;

import java.util.Random;

public abstract class PointGame {
    
    MorseParser parser;
    Random random;
    String[] prompts;
    int points = 0;
    int errors = 0;
    boolean ongoing = false;

    public PointGame(MorseParser parser, long seed) {
        this.parser = parser;
        this.random = new Random(seed);        
    }
    
    public PointGame(MorseParser parser) {
        this.parser = parser;
        this.random = new Random();
    }
    
    public String[] nextPrompt() {
        int i = random.nextInt(prompts.length);
        String letter = prompts[i];
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
