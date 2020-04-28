package remorse.domain;

import java.util.HashMap;
import java.util.Scanner;

/**
* Luokka mahdollistaa käännettävän aakkoston lukemisen tiedostosta.
*/
public class AlphabetLoader {
    
    /**
     * Metodi lukee annetun tiedoston ja luo aakkostosta hajautustaulun.
     * 
     * @param file Luettava tiedosto
     * @return Aakkosto ja käännökset hajautustaulussa
     */
    public HashMap<Character, String> loadAlphabet(String file) {
        HashMap<Character, String> alphabet = new HashMap<>();
        try (Scanner alphabetScanner = new Scanner(getClass().getResourceAsStream(file), "utf-8")) {
            while (alphabetScanner.hasNextLine()) {
                String line = alphabetScanner.nextLine();
                String[] pair = line.split(" ");
                alphabet.put(pair[0].charAt(0), pair[1]);
            }
        } catch (Exception e) {
            System.out.println("Virhe aakkostoa ladatessa.");
        }
        return alphabet;
    }   
    
}
