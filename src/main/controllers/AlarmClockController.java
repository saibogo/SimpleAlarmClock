package main.controllers;

import main.models.clockModels.AlarmClock;

public class AlarmClockController extends Thread {

    protected AlarmClock alarmClock;
    protected boolean alarmClockRun = true;

    public AlarmClockController(final AlarmClock alarmClock) {
        this.alarmClock = alarmClock;
    }

    public AlarmClock getAlarmClock() {
        return alarmClock;
    }

    @Override
    public void run() {
        while (alarmClockRun) {
            assert this.alarmClock != null;
            if (this.alarmClock.alarmIsRun()) this.alarmClock.sayBeep();
            else {
                try {
                    int PAUSE_TIME_SOUND = 2000;
                    Thread.sleep(PAUSE_TIME_SOUND);
                } catch (InterruptedException e) {
                    this.alarmClock = null;
                }
            }
        }
        if (this.alarmClock != null) {
            this.alarmClock.destructor();
        }
    }


    public void stopAlarmClock() {
        this.alarmClockRun = false;
        this.alarmClock.destructor();
        this.interrupt();
    }

}
