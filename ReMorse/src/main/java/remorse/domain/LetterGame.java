package remorse.domain;

public class LetterGame extends PointGame {    
        
    public LetterGame(MorseParser parser, long seed) {
        super(parser, seed);
        this.prompts = new String[] {
            "a", "b", "c", "d", "e", "f", "g", "h", "i", 
            "j", "k", "l", "m", "n", "o", "p", "q", "r", 
            "s", "t", "v", "w", "x", "y", "z", "å", "ä", "ö"};
    }
    
    public LetterGame(MorseParser parser) {
        super(parser);
        this.prompts = new String[] {
            "a", "b", "c", "d", "e", "f", "g", "h", "i", 
            "j", "k", "l", "m", "n", "o", "p", "q", "r", 
            "s", "t", "v", "w", "x", "y", "z", "å", "ä", "ö"};
    }
        
}
