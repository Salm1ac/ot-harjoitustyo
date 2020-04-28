package remorse.domain;

import java.util.HashMap;

/**
 * Luokka mahdollistaa sanojen parsimisen annetusta aakkostosta toiseen.
 */
public class Parser {
    
    private final HashMap<Character, String> alphabet;

    /**
     * Konstruktori luo parserin annetulla aakkostolla.
     * @param alphabet 
     */
    public Parser(HashMap alphabet) {
        this.alphabet = alphabet;
    }
    
    /**
     * Metodi parsii annetun sanan uuteen aakkostoon ja asettaa 
     * välilyöntejä merkkien väliin.
     * @param s Parsittava sana
     * @return Sana uudessa aakkostossa
     */
    public String parseString(String s) {
        StringBuilder parsed = new StringBuilder();
        char[] chars = s.toLowerCase().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            parsed.append(this.alphabet.getOrDefault(chars[i], "VIRHE"));
            parsed.append(" ");
        }
        return parsed.toString().trim();
    }
    
}
