/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remorse.domain;

import java.util.ArrayList;

/**
 *
 * @author risto
 */
public class MorseSequence {
    
    private final int dotLength = 1;
    private final int dashLength = 3;
    private final int letterSpaceLength = 1;
    private final int wordSpaceLength = 7;

    private String morseString;
    private ArrayList<Boolean> sequenceBits;
    private int nextIndex;

    public MorseSequence() {
        this.morseString = "";
        this.sequenceBits = new ArrayList<>();
        this.sequenceBits.add(false);
        this.nextIndex = 0;
    }   
        
    public void createSequence(String morse) {
        this.morseString = morse;
        this.nextIndex = 0;
        this.sequenceBits.clear();
        for (int i = 0; i < morse.length(); i++) {
            char c = morse.charAt(i);
            if (c == '.') {
                for (int j = 0; j < 16 * dotLength; j++) {
                    sequenceBits.add(true);
                }
            } else if (c == '-') {
                for (int j = 0; j < 16 * dashLength; j++) {
                    sequenceBits.add(true);
                }
            }           
            for (int j = 0; j < 16 * letterSpaceLength; j++) {
                sequenceBits.add(false);
            }
        }
        for (int j = 0; j < 16 * wordSpaceLength; j++) {
            sequenceBits.add(false);
        }
    }
    
    public boolean nextBit() {
        boolean nextBit = sequenceBits.get(nextIndex);
        nextIndex = (nextIndex + 1) % sequenceBits.size();
        return nextBit;
    }
    
    
   
    
    
    
}
