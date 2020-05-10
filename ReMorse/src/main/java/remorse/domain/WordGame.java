package remorse.domain;

import remorse.data.DatabaseHandler;

/**
 * Luokka on PointGamen erikoistapaus, jossa kysytään sanoja.
 */
public class WordGame extends PointGame {    

    /**
     * Konstruktori luo testausta varten uuden valesatunnaisen PointGamen, 
     * jossa kysytään sanoja.
     * @param parser Käytettävä parseri
     * @param seed Satunnaislukugeneraattorin alkuarvo
     * @see remorse.domain.PointGame
     * @see remorse.domain.Parser
     */
    /*
    public WordGame(Parser parser, long seed) {
        super(parser, seed);
        this.prompts = new String[] {
            "kenttä", "potku", "kettu", "hissi", "katko", "takoi",
            "rikos", "pirinä", "pentti", "heheh", "pihdit",
            "koita", "tulos", "pommi", "passi", "lahti"}; 
    }
    */
    
    /**
     * Konstruktori luo uuden satunnaisen PointGamen, jossa kysytään sanoja.
     * @param parser Käytettävä parseri
     * @param dbHandler Käytettävä tietokannan käsittelijä
     * @see remorse.data.DatabaseHandler
     * @see remorse.domain.PointGame
     * @see remorse.domain.Parser
     */
    public WordGame(Parser parser, DatabaseHandler dbHandler) {
        super(parser, dbHandler);
        this.type = "word";
    }
    
    /** 
     * Metodi hakee tietokannan käsittelijän avulla satunnaisen sanan ja parsii sen.
     * @return Palauttaa parin, jossa on sana ja sen parsittu vastine
     * @see remorse.data.DatabaseHandler#nextWord() 
     */
    public String[] nextWord() {
        String original = dbHandler.nextWord();
        String parsed = parser.parseString(original);
        return new String[] {original, parsed};
    }
    
    
        
}
