package remorse.domain;

import java.util.Random;

/**
 * Luokka tarjoaa pelin, jossa saadaan pisteitä oikeista vastauksista.
 * Peli osaa tehdä uusia kysymys-vastaus-pareja, tarkistaa vastauksia
 * ja antaa pisteitä tai virheitä. Peli päättyy tietystä virhemäärästä.  
 */
public abstract class PointGame {
    
    Parser parser;
    Random random;
    String[] prompts;
    int points = 0;
    int errors = 0;
    boolean ongoing = false;
    
    int maxErrors = 3;

    /**
     * Konstruktori luo uuden valesatunnaisen pelin testausta varten.
     * @param parser Käytettävä parseri
     * @param seed Satunnaislukugeneraattorin alkuarvo
     * @see remorse.domain.Parser
     */
    public PointGame(Parser parser, long seed) {
        this.parser = parser;
        this.random = new Random(seed);        
    }
    
    /**
     * Konstruktori luo uuden satunnaisen pelin.
     * @param parser Käytettävä parseri
     * @see remorse.domain.Parser
     */
    public PointGame(Parser parser) {
        this.parser = parser;
        this.random = new Random();
    }
    
    /**
     * Metodi arpoo satunnaisen sanan ja parsii sen.
     * @return Pari, jossa on satunnainen sana ja sen parsittu vastine
     * @see remorse.domain.Parser#parseString(java.lang.String) 
     */
    public String[] nextPrompt() {
        int i = random.nextInt(prompts.length);
        String original = prompts[i];
        String parsed = parser.parseString(original);
        return new String[] {original, parsed};
    }
    
    /**
     * Metodi tarkistaa, onko arvaus oikein ja päivittää pelitilanteen.
     * @param guess Käyttäjän arvaus
     * @param correct Oikea vastaus
     * @return True, jos arvaus oli oikein
     */
    public boolean checkGuess(String guess, String correct) {
        if (guess.equalsIgnoreCase(correct)) {
            points++;
            return true;
        } else {
            errors++;
            if (errors >= maxErrors) {
                ongoing = false;
            }
            return false;
        }
    }    
    
    /**
     * Metodi aloittaa uuden pelin.
     */
    public void newGame() {
        errors = 0;
        points = 0;
        ongoing = true;
    }
    
    /**
     * Metodi lopettaa pelin.
     */
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

    public void setMaxErrors(int maxErrors) {
        this.maxErrors = maxErrors;
    }   
        
}
