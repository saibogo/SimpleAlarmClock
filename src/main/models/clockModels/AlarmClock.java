package main.models.clockModels;

import main.models.supportModels.Triplet;
import main.myException.TripletBuildException;
import main.support.SupportClass;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.util.Date;

public class AlarmClock {

    protected Date alarmDate;
    protected Synthesizer synthesizer;
    protected MidiChannel[] channels;
    protected final String name;
    protected final int note;
    protected final int instrument;
    protected boolean stoppedStatus = false;
    protected boolean notCreatedDialog = false;
    protected final int volume;
    protected Triplet<Long> timeTriplet;

    {
        try {
            timeTriplet = new Triplet.Builder<Long>().setFirst(0L).setSecond(0L).setLast(0L).build();
        } catch (TripletBuildException e) {
            e.printStackTrace();
        }
    }

    protected AlarmClock(Builder builder) {
        this.alarmDate = builder.alarmDate;
        this.name = builder.name;
        this.note = builder.note;
        this.instrument = builder.instrument;
        this.volume = builder.volume;

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
        this.synthesizer.close();
        
    }

    public boolean alarmIsRun() {
        try {
            return alarmDate.getTime() <= System.currentTimeMillis();
        } catch (NullPointerException e) {
            return true;
        }
    }

    public long secondsLeft() {
        return (alarmDate.getTime() - System.currentTimeMillis()) / 1000;
    }

    public void appendTime(final long deltaTime) {
        this.alarmDate.setTime(System.currentTimeMillis() + deltaTime);
    }

    public Triplet<Long> getTimeTriplet() {
        long deltaTime = this.alarmDate.getTime() - System.currentTimeMillis();
        deltaTime = (deltaTime >= 0 ? deltaTime : 0) / 1000L;
        
        this.timeTriplet.setFirst(deltaTime / 3600L);
        this.timeTriplet.setSecond((deltaTime % 3600L) / 60L);
        this.timeTriplet.setLast((deltaTime % 3600L) % 60L);
        return this.timeTriplet;
    }

    public void sayBeep() {
        try {
            if (!this.stoppedStatus) {
                int channelNumber = this.instrument <= SupportClass.normalInstrumentMaxNumber ? 0 : 10;
                int instrumentNumber = channelNumber == 10 ?
                        this.instrument - SupportClass.normalInstrumentMaxNumber + 35 : this.instrument;
                channels[channelNumber].programChange(instrumentNumber);
                channels[channelNumber].noteOn(this.note, this.volume);
                int TIME_SOUND = 750;
                Thread.sleep(TIME_SOUND); // in milliseconds
                channels[channelNumber].noteOff(this.note);
            }
        }  catch (InterruptedException ignored) {
        }
    }

    public boolean isNotCreatedDialog() {
        return notCreatedDialog;
    }

    public void setNotCreatedDialog(boolean notCreatedDialog) {
        this.notCreatedDialog = notCreatedDialog;
    }

    public static class Builder {

        private Date alarmDate = new Date();
        private String name = "";
        private int note = 43;
        private int instrument = 47;
        private int volume = 80;

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

        public Builder setVolume(int volume) {
            this.volume = volume;
            return this;
        }
    }

}
