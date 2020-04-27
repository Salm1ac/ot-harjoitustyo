package remorse.domain;

public class WordGame extends PointGame {    

    public WordGame(MorseParser parser, long seed) {
        super(parser, seed);
        this.prompts = new String[] {
            "kenttä", "potku", "kettu", "hissi", "katko", "takoi",
            "rikos", "pirinä", "pentti", "heheh", "pihdit",
            "koita", "tulos", "pommi", "passi", "lahti"}; 
    }
    
    public WordGame(MorseParser parser) {
        super(parser);
        this.prompts = new String[] {
            "kenttä", "potku", "kettu", "hissi", "katko", "takoi",
            "rikos", "pirinä", "pentti", "heheh", "pihdit",
            "koita", "tulos", "pommi", "passi", "lahti"}; 
    }
        
}
