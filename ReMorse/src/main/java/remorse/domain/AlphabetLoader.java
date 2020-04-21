/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remorse.domain;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author risto
 */
public class AlphabetLoader {
    
    public HashMap<Character, String> loadAlphabet(String file) {
        HashMap<Character, String> alphabet = new HashMap<>();
        try (Scanner alphabetScanner = new Scanner(getClass().getResourceAsStream(file), "utf-8")) {
            while (alphabetScanner.hasNextLine()) {
                String line = alphabetScanner.nextLine();
                String[] pair = line.split(" ");
                alphabet.put(pair[0].charAt(0), pair[1]);
            }
        } catch (Exception e) {            
        }
        return alphabet;
    }
    
    
    
}
