/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remorse;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;
import remorse.domain.MorseParser;

/**
 *
 * @author risto
 */
public class Main {
    public static void main(String[] args) {
        HashMap<Character, String> alphabet = new HashMap<>();
        try(Scanner alphabetScanner = new Scanner(Paths.get("alphabet.txt"))) {
            while(alphabetScanner.hasNextLine()) {
                String line = alphabetScanner.nextLine();
                String[] pair = line.split(" ");
                alphabet.put(pair[0].charAt(0), pair[1]);
            }
        } catch (Exception e) {
            System.out.println("Virhe tiedostoa luettaessa: " + e.getMessage());
        }
        MorseParser parser = new MorseParser(alphabet);
        System.out.println(parser.parseString("Hei"));
    }
}
