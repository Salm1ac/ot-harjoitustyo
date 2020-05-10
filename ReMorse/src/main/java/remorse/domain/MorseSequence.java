package remorse.domain;

import java.util.ArrayList;

/**
 * Luokka mahdollistaa morsekoodin muuttamisen bittijonoksi.
 */
public class MorseSequence {
    
    private final int dotLength = 1;
    private final int dashLength = 3;
    private final int letterSpaceLength = 1;
    private final int wordSpaceLength = 6; // 7-1
    
    private int timeUnit = 16;

    private ArrayList<Boolean> sequenceBits;
    private int nextIndex;
    
    /**
     * Konstruktori luo pelkästä nollabitistä koostuvan jonon.
     */
    public MorseSequence() {
        this.sequenceBits = new ArrayList<>();
        this.sequenceBits.add(false);
        this.nextIndex = 0;
    }   
    
    /**
     * Metodi lisää bittijonoon annettua bittiä halutun määrän 
     * skaalattuna aikayksikön pituudella.
     * @param b Lisättävä bitti
     * @param amount Lisättävien bittien määrä
     */
    private void sequenceAdd(boolean b, int amount) {
        for (int j = 0; j < timeUnit * amount; j++) {
            sequenceBits.add(b);
        }
    }
    
    /**
     * Metodi muodostaa annetusta morsekoodista bittijonon.
     * @param morse Käännettävä morsekoodi
     * @see remorse.domain.MorseSequence#sequenceAdd(boolean, int) 
     */
    public void createSequence(String morse) {
        this.nextIndex = 0;
        this.sequenceBits.clear();
        for (int i = 0; i < morse.length(); i++) {
            char c = morse.charAt(i);
            if (c == '.') {
                sequenceAdd(true, dotLength);
            } else if (c == '-') {
                sequenceAdd(true, dashLength);
            }           
            sequenceAdd(false, letterSpaceLength);
        }
        sequenceAdd(false, wordSpaceLength);
    }
    
    /**
     * Metodi palauttaa bittijonon seuraavan bitin.
     * @return Seuraava bittijonon bitti
     */
    public boolean nextBit() {
        boolean nextBit = sequenceBits.get(nextIndex);
        nextIndex = (nextIndex + 1) % sequenceBits.size();
        return nextBit;
    }
    
    /**
     * Metodi asettaa käytetyn aikayksikön pituuden.
     * Oletusarvo 16 vastaa n. 16/60 = 0.27 sekuntia.
     * @param timeUnit Aikayksikön uusi pituus, noin timeUnit/60 s.
     */
    public void setTimeUnit(int timeUnit) {
        if (timeUnit > 0) {
            this.timeUnit = timeUnit;
        }
    }
    
   
    
    
    
}
