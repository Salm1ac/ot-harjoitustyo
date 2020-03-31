/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remorse.domain;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author risto
 */
public class MorseParserTest {
    
    MorseParser parser;
    
    @Before
    public void setUp() {
        HashMap<Character, String> alphabet = new HashMap<>();
        try(Scanner alphabetScanner = new Scanner(Paths.get("alphabet.txt"), "utf-8")) {
            while(alphabetScanner.hasNextLine()) {
                String line = alphabetScanner.nextLine();
                String[] pair = line.split(" ");
                alphabet.put(pair[0].charAt(0), pair[1]);
            }
        } catch (Exception e) {
        }
        parser = new MorseParser(alphabet);
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void parserReturnsCorrectString() {
        assertEquals("-.- .. ... ... .-", parser.parseString("kissa"));
    }
    
    @Test
    public void parserIgnoresCase() {
        assertEquals(".--. . ... ..- ... .. . -. ..", parser.parseString("pEsuSIenI"));
    }
}
