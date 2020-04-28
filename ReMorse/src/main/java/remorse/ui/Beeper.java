package remorse.ui;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

/**
 * Luokka mahdollistaa yksinkertaisen äänen toiston.
 * @author risto
 */
public class Beeper {
    
    private Synthesizer synth;
    private MidiChannel chan;
    private boolean on = false;
    
    /**
     * Konstruktori avaa MIDI-syntetisaattorin ja asettaa ensimmäisen 
     * kanavan instrumentiksi siniaaltoa muistuttavan Whistlen.
     */
    public Beeper() {
        try  {
            synth = MidiSystem.getSynthesizer();
            synth.open();
            chan = synth.getChannels()[0];
            chan.programChange(79);
        } catch (Exception e) {
            System.out.println("Ilmeni MIDI-ongelma.");
        }
    }
    
    /**
     * Metodi aloittaa 440 Hz äänen toiston kohtalaisen kovaa.
     */
    public void turnOn() {
        chan.noteOn(69, 100);
        on = true;
    }
    
    /**
     * Metodi pysäyttää äänen toiston.
     */
    public void turnOff() {
        chan.noteOff(69);
        on = false;
    }

    public boolean isOn() {
        return on;
    }
    
    
    
    
    
}
