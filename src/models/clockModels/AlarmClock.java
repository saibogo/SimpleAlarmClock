package models.clockModels;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.util.Date;

public class AlarmClock {

    private static int VOLUME = 80;
    private static int TIME_SOUND = 750;

    private Date alarmDate;
    private Synthesizer synthesizer;
    private MidiChannel[] channels;
    private String name;
    private int note;
    private int instrument;
    private boolean stoppedStatus = false;

    private AlarmClock(Builder builder) {
        this.alarmDate = builder.alarmDate;
        this.name = builder.name;
        this.note = builder.note;
        this.instrument = builder.instrument;

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
        this.stoppedStatus = true;

    }

    public boolean alarmIsRun() {
        try {
            return alarmDate.getTime() <= (new Date()).getTime();
        } catch (NullPointerException e) {
            return true;
        }
    }

    public long secondsLeft() {
        return (alarmDate.getTime() - (new Date().getTime())) / 1000;
    }

    public void appendTime(final long deltaTime) {
        this.alarmDate = new Date((new Date()).getTime() + deltaTime);
    }

    public void sayBeep() {
        try {
            if (!this.stoppedStatus) {
                channels[0].programChange(this.instrument);
                channels[0].noteOn(this.note, VOLUME);
                Thread.sleep(TIME_SOUND); // in milliseconds
                channels[0].noteOff(this.note);
            }
        }  catch (InterruptedException ignored) {
        }
    }

    public static class Builder {

        private Date alarmDate = new Date();
        private String name = "";
        private int note = 43;
        private int instrument = 47;

        public Builder() {
        }

        public AlarmClock build() {
            return new AlarmClock(this);
        }

        public Builder setAlarmDate(final Date alarmDate) {
            this.alarmDate = alarmDate;
            return this;
        }

        public Builder setName(final String name) {
            this.name = name;
            return this;
        }

        public Builder setNote(int note) {
            this.note = note;
            return this;
        }

        public Builder setInstrument(int instrument) {
            this.instrument = instrument;
            return this;
        }
    }
}
