package remorse.domain;

/**
 * Luokka on PointGamen erikoistapaus, jossa kysytään kirjaimia.
 */
public class LetterGame extends PointGame {    
    
    /**
     * Konstruktori luo testausta varten uuden valesatunnaisen PointGamen, 
     * jossa kysytään kirjaimia.
     * @param parser Käytettävä parseri
     * @param seed Satunnaislukugeneraattorin alkuarvo
     * @see remorse.domain.PointGame
     * @see remorse.domain.Parser
     */
    public LetterGame(Parser parser, long seed) {
        super(parser, seed);
        this.prompts = new String[] {
            "a", "b", "c", "d", "e", "f", "g", "h", "i", 
            "j", "k", "l", "m", "n", "o", "p", "q", "r", 
            "s", "t", "v", "w", "x", "y", "z", "å", "ä", "ö"};
    }
    
    /**
     * Konstruktori luo uuden satunnaisen PointGamen, jossa kysytään kirjaimia.
     * @param parser Käytettävä parseri
     * @see remorse.domain.PointGame
     * @see remorse.domain.Parser
     */
    public LetterGame(Parser parser) {
        super(parser);
        this.prompts = new String[] {
            "a", "b", "c", "d", "e", "f", "g", "h", "i", 
            "j", "k", "l", "m", "n", "o", "p", "q", "r", 
            "s", "t", "v", "w", "x", "y", "z", "å", "ä", "ö"};
    }
        
}
