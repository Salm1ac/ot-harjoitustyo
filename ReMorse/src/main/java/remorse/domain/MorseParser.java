package remorse.domain;

import java.util.HashMap;

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
