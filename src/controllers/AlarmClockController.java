package controllers;

import models.clockModels.AlarmClock;

public class AlarmClockController extends Thread {

    private static int PAUSE_TIME_SOUND = 2000;

    private AlarmClock alarmClock;

    public AlarmClockController(final AlarmClock alarmClock) {
        this.alarmClock = alarmClock;
    }

    public AlarmClock getAlarmClock() {
        return alarmClock;
    }

    @Override
    public void run() {
        int countBeep = 10;
        while (countBeep > 0) {
            if (this.alarmClock.alarmIsRun()) {
                countBeep--;
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


}
