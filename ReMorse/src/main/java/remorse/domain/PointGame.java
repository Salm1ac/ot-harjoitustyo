package remorse.domain;

import remorse.data.DatabaseHandler;

/**
 * Luokka tarjoaa pelin, jossa saadaan pisteitä oikeista vastauksista.
 * Peli osaa tehdä uusia kysymys-vastaus-pareja, tarkistaa vastauksia
 * ja antaa pisteitä tai virheitä. Peli päättyy tietystä virhemäärästä.  
 */
public abstract class PointGame {
    
    Parser parser;
    DatabaseHandler dbHandler;
    String type = "generic";
    int points = 0;
    int errors = 0;
    boolean ongoing = false;
    
    int maxErrors = 2;
    
    /**
     * Konstruktori luo uuden satunnaisen pelin.
     * @param parser Käytettävä parseri
     * @param dbHandler Käytettävä tietokannan käsittelijä
     * @see remorse.domain.Parser
     */
    public PointGame(Parser parser, DatabaseHandler dbHandler) {
        this.parser = parser;
        this.dbHandler = dbHandler;
    }
    
    /**
     * Metodi tarkistaa, onko arvaus oikein ja päivittää pelitilanteen. 
     * Pelin päättyessä tulos talletetaan.
     * @param guess Käyttäjän arvaus
     * @param correct Oikea vastaus
     * @return True, jos arvaus oli oikein
     * @see remorse.data.DatabaseHandler#saveScore(int, java.lang.String) 
     */
    public boolean checkGuess(String guess, String correct) {
        if (guess.equalsIgnoreCase(correct)) {
            points++;
            return true;
        } else {
            errors++;
            if (errors > maxErrors) {
                dbHandler.saveScore(points, type);
                ongoing = false;
            }
            return false;
        }
    }    
    
    /**
     * Metodi aloittaa uuden pelin. 
     * Jos pelaajalla on pisteitä, ne talletetaan.
     * @see remorse.data.DatabaseHandler#saveScore(int, java.lang.String) 
     */
    public void newGame() {
        if (ongoing && points > 0) {
            dbHandler.saveScore(points, type);
        } 
        errors = 0;
        points = 0;
        ongoing = true;
    }
    
    /**
     * Metodi lopettaa pelin.
     * Jos pelaajalla on pisteitä, ne talletetaan.
     * @see remorse.data.DatabaseHandler#saveScore(int, java.lang.String) 
     */
    public void stopGame() {
        if (ongoing && points > 0) {
            dbHandler.saveScore(points, type);
        }        
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
