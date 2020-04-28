package remorse.ui;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

public class Beeper {
    
    private Synthesizer synth;
    private MidiChannel chan;
    private boolean on = false;
    
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
    
    public void turnOn() {
        chan.noteOn(69, 100);
        on = true;
    }
    
    public void turnOff() {
        chan.noteOff(69);
        on = false;
    }

    public boolean isOn() {
        return on;
    }
    
    
    
    
    
}
