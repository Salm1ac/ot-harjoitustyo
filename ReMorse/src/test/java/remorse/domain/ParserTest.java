package remorse.domain;

import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ParserTest {
    
    Parser parser;
    
    @Before
    public void setUp() {
        AlphabetLoader alphabetLoader = new AlphabetLoader();
        HashMap<Character, String> alphabet = alphabetLoader.loadAlphabet("/alphabets/alphabet.txt");        
        parser = new Parser(alphabet);
    }
       
    @Test
    public void parserReturnsCorrectString() {
        assertEquals("-.- .. ... ... .-", parser.parseString("kissa"));
    }
    
    @Test
    public void parserIgnoresCase() {
        assertEquals(".--. . ... ..- ... .. . -. ..", parser.parseString("pEsuSIenI"));
    }
}
