/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remorse.data;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author risto
 */
public class DatabaseHandlerTest {
    
    DatabaseHandler dbHandler;
        
    @Before
    public void setUp() {
        dbHandler = new DatabaseHandler("jdbc:sqlite:empty.db");
    }
    
    @Test
    public void emptyDatabaseProducesErrorWord() {
        assertEquals("virhe", dbHandler.nextWord());
    }
    

}
