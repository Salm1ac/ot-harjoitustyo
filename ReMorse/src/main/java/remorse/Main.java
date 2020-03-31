/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remorse;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import remorse.domain.MorseParser;

/**
 *
 * @author risto
 */
public class Main {
    public static void main(String[] args) {
        HashMap<Character, String> alphabet = new HashMap<>();
        try(Scanner alphabetScanner = new Scanner(Paths.get("alphabet.txt"), "utf-8")) {
            while(alphabetScanner.hasNextLine()) {
                String line = alphabetScanner.nextLine();
                String[] pair = line.split(" ");
                alphabet.put(pair[0].charAt(0), pair[1]);
            }
        } catch (Exception e) {
            System.out.println("Virhe tiedostoa luettaessa: " + e.getMessage());
        }
        MorseParser parser = new MorseParser(alphabet);
        Scanner in = new Scanner(System.in);
        Random r = new Random();
        // TODO: add more words via file
        String[] words = {"kenttä", "potku", "kettu", "hissi", "katko", "takoi",
                            "rikos", "pirinä", "pentti", "heheh", "pihdit",
                            "koita", "tulos", "pommi", "passi", "lahti"};
        String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", 
                            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                            "v", "w", "x", "y", "z", "å", "ä", "ö"};
        System.out.println("Tervetuloa opettelemaan morsekoodia ReMorse-ohjelmalla!");
        System.out.println("Kirjoita 1, jos haluat tunnistaa kirjaimia.");
        System.out.println("Kirjoita 2, jos haluat tunnistaa sanoja.");
        System.out.println("Kirjoita jotain muuta, jos haluat poistua.");
        String mode = in.nextLine();
        if (mode.equals("1")) {
            System.out.println("Kirjoita morsekoodia vastaava kirjain.");
            int points = 0;
            boolean end = false;
            while (!end) {
                String correct = letters[r.nextInt(letters.length)];
                String morse = parser.parseString(correct);
                System.out.println(morse);
                String guess = in.nextLine();
                if (guess.equalsIgnoreCase(correct)) points++;
                else end = true;
            }
            System.out.println("Sait " + points + " kirjainta oikein.");
            
        } else if (mode.equals("2")) {
            System.out.println("Kirjoita morsekoodia vastaava sana.");
            int points = 0;
            boolean end = false;
            while (!end) {
                String correct = words[r.nextInt(words.length)];
                String morse = parser.parseString(correct);
                System.out.println(morse);
                String guess = in.nextLine();
                if (guess.equalsIgnoreCase(correct)) points++;
                else end = true;
            }
            System.out.println("Sait " + points + " sanaa oikein.");
        }
        
        System.out.println("Näkemiin!");
        
    }
}
