/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remorse.domain;

import java.util.HashMap;
import java.lang.StringBuilder;

/**
 *
 * @author risto
 */
public class MorseParser {
    private final HashMap<Character, String> alphabet;

    public MorseParser(HashMap alphabet) {
        this.alphabet = alphabet;
    }
    
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
