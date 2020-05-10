package remorse.ui;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

/**
 * Luokka mahdollistaa yksinkertaisen äänen toiston.
 */
public class Beeper {
    
    private Synthesizer synth;
    private MidiChannel chan;
    private boolean on = false;
    
    private int note = 69;
    private int volume = 100;
    
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
     * Metodi aloittaa äänen toiston.
     * Oletuksena on nuotti 69 eli viritys-A.
     */
    public void turnOn() {
        chan.noteOn(note, volume);
        on = true;
    }
    
    /**
     * Metodi pysäyttää äänen toiston.
     */
    public void turnOff() {
        chan.noteOff(note);
        on = false;
    }

    public boolean isOn() {
        return on;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
    
    
}
