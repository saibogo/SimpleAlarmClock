package models;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.util.Date;

public class AlarmClock {

    private static int NOTE = 43;
    private static int VOLUME = 80;
    private static int TIME_SOUND = 750;
    private static int MUSICAL_INSTRUMENT = 47;

    private Date alarmDate;
    private Synthesizer synthesizer;
    private MidiChannel[] channels;
    private String name;

    public AlarmClock(final String name, final Date alarmDate) {
        this.alarmDate = alarmDate;
        this.name = name;
        try {
            this.synthesizer = MidiSystem.getSynthesizer();
            this.synthesizer.open();
            this.channels = this.synthesizer.getChannels();
        } catch (MidiUnavailableException e) {
            System.out.println("Not found midi system");
        }
    }

    public String getName() {
        return this.name;
    }

    public Date getAlarmDate() {
        return this.alarmDate;
    }

    public void destructor() {
        this.alarmDate = null;
        this.synthesizer.close();
        this.channels = null;
        this.synthesizer = null;
    }

    public boolean alarmIsRun() {
        return alarmDate.getTime() <= (new Date()).getTime();
    }

    public long secondsLeft() {
        return (alarmDate.getTime() - (new Date().getTime())) / 1000;
    }

    public void sayBeep() {
        try {

            channels[0].programChange(MUSICAL_INSTRUMENT);
            channels[0].noteOn(NOTE, VOLUME);
            Thread.sleep(TIME_SOUND); // in milliseconds
            channels[0].noteOff(NOTE);
        }  catch (InterruptedException e) {
            System.out.println("Not found midi system");
        }
    }
}
