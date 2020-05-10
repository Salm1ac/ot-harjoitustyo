package remorse.domain;

import java.util.Random;
import remorse.data.DatabaseHandler;

/**
 * Luokka on PointGamen erikoistapaus, jossa kysytään kirjaimia.
 */
public class LetterGame extends PointGame {    
    
    private final Random random;
    private final String[] prompts = new String[] {
            "a", "b", "c", "d", "e", "f", "g", "h", "i", 
            "j", "k", "l", "m", "n", "o", "p", "q", "r", 
            "s", "t", "v", "w", "x", "y", "z", "å", "ä", "ö"};
    
    /**
     * Konstruktori luo testausta varten uuden valesatunnaisen PointGamen, 
     * jossa kysytään kirjaimia.
     * @param parser Käytettävä parseri
     * @param dbHandler Käytettävä tietokannan käsittelijä
     * @param seed Satunnaislukugeneraattorin alkuarvo
     * @see remorse.domain.PointGame
     * @see remorse.domain.Parser
     */
    public LetterGame(Parser parser, DatabaseHandler dbHandler, long seed) {
        super(parser, dbHandler);
        this.random = new Random(seed);
        this.type = "letter";
    }
    
    /**
     * Konstruktori luo uuden satunnaisen PointGamen, jossa kysytään kirjaimia.
     * @param parser Käytettävä parseri
     * @param dbHandler Käytettävä tietokannan käsittelijä
     * @see remorse.domain.PointGame
     * @see remorse.domain.Parser
     */
    public LetterGame(Parser parser, DatabaseHandler dbHandler) {
        super(parser, dbHandler);
        this.random = new Random();
        this.type = "letter";
    }
    
    /**
     * Metodi arpoo satunnaisen sanan ja parsii sen.
     * @return Pari, jossa on satunnainen sana ja sen parsittu vastine
     * @see remorse.domain.Parser#parseString(java.lang.String) 
     */
    public String[] nextLetter() {
        int i = random.nextInt(prompts.length);
        String original = prompts[i];
        String parsed = parser.parseString(original);
        return new String[] {original, parsed};
    }
        
}
