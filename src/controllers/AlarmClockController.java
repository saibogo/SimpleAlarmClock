package controllers;

import models.clockModels.AlarmClock;

public class AlarmClockController extends Thread {

    private static int PAUSE_TIME_SOUND = 2000;

    private AlarmClock alarmClock;
    private boolean alarmClockRun = true;

    public AlarmClockController(final AlarmClock alarmClock) {
        this.alarmClock = alarmClock;
    }

    public AlarmClock getAlarmClock() {
        return alarmClock;
    }

    @Override
    public void run() {
        while (alarmClockRun) {
            if (this.alarmClock.alarmIsRun()) {
                this.alarmClock.sayBeep();
            } else {
                try {
                    Thread.sleep(PAUSE_TIME_SOUND);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        this.alarmClock.destructor();
    }


    public void stopAlarmClock() {
        this.alarmClockRun = false;
        this.alarmClock.destructor();
        this.interrupt();
    }

}
