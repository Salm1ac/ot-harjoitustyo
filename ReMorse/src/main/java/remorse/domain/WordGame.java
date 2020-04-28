package remorse.domain;

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
    public WordGame(Parser parser, long seed) {
        super(parser, seed);
        this.prompts = new String[] {
            "kenttä", "potku", "kettu", "hissi", "katko", "takoi",
            "rikos", "pirinä", "pentti", "heheh", "pihdit",
            "koita", "tulos", "pommi", "passi", "lahti"}; 
    }
    
    /**
     * Konstruktori luo uuden satunnaisen PointGamen, jossa kysytään sanoja.
     * @param parser Käytettävä parseri
     * @see remorse.domain.PointGame
     * @see remorse.domain.Parser
     */
    public WordGame(Parser parser) {
        super(parser);
        this.prompts = new String[] {
            "kenttä", "potku", "kettu", "hissi", "katko", "takoi",
            "rikos", "pirinä", "pentti", "heheh", "pihdit",
            "koita", "tulos", "pommi", "passi", "lahti"}; 
    }
        
}
