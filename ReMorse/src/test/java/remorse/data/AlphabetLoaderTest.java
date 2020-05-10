/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remorse.data;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author risto
 */
public class AlphabetLoaderTest {
    
    AlphabetLoader loader;
    
    @Before
    public void setUp() {
        loader = new AlphabetLoader();
    }
    
    @Test
    public void defaultAlphabetLoads() {
        HashMap<Character, String> alphabet = loader.loadAlphabet("/alphabets/alphabet.txt");
        assertEquals(false, alphabet.isEmpty());        
    }
    
    @Test
    public void emptyAlphabetForNullFile() {
        HashMap<Character, String> alphabet = loader.loadAlphabet(null);
        assertEquals(true, alphabet.isEmpty());        
    }
    
}
