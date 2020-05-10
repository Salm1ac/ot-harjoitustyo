package remorse.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class MorseSequenceTest {
    
    MorseSequence seq;
    String str;
    
    @Before
    public void setUp() {
        seq = new MorseSequence();
        str = ". -";
    }
    
    @Test
    public void correctSequenceCreated() {
        seq.createSequence(str);
        for (int i = 0; i < 16; i++) {
            assertEquals(true, seq.nextBit());
        }
        for (int i = 0; i < 32; i++) {
            assertEquals(false, seq.nextBit());
        }
        for (int i = 0; i < 48; i++) {
            assertEquals(true, seq.nextBit());
        }        
        for (int i = 0; i < 96; i++) {
            assertEquals(false, seq.nextBit());
        }
        
    }
    
    @Test
    public void correctSequenceCreatedWithTimeUnit30() {
        seq.setTimeUnit(30);
        seq.createSequence(str);
        for (int i = 0; i < 30; i++) {
            assertEquals(true, seq.nextBit());
        }
        for (int i = 0; i < 60; i++) {
            assertEquals(false, seq.nextBit());
        }
        for (int i = 0; i < 90; i++) {
            assertEquals(true, seq.nextBit());
        }        
        for (int i = 0; i < 180; i++) {
            assertEquals(false, seq.nextBit());
        }
        
    }
    
    @Test
    public void zeroTimeUnitIsDisregarded() {
        seq.setTimeUnit(0);
        seq.createSequence(str);
        for (int i = 0; i < 16; i++) {
            assertEquals(true, seq.nextBit());
        }
        for (int i = 0; i < 32; i++) {
            assertEquals(false, seq.nextBit());
        }
        for (int i = 0; i < 48; i++) {
            assertEquals(true, seq.nextBit());
        }        
        for (int i = 0; i < 96; i++) {
            assertEquals(false, seq.nextBit());
        }
        
    }
    
    @Test
    public void nonMorseIsOnlyFalse() {
        seq.createSequence("hei");
        for (int i = 0; i < 200; i++) {
            assertEquals(false, seq.nextBit());
        }
    }
    
    
}
