package main.controllers;

import main.models.clockModels.Timer;

public class TimerController extends Thread {

    private Timer timer;
    private boolean timerRun = true;

    public TimerController(final Timer timer) {
       this.timer = timer;
    }

    public Timer getTimer() {
        return timer;
    }

    @Override
    public void run() {
       while (this.timerRun) {
            if (this.timer != null && this.timer.alarmIsRun()) this.timer.sayBeep();
            else {
                try {
                    int PAUSE_TIME_SOUND = 2000;
                    Thread.sleep(PAUSE_TIME_SOUND);
                } catch (InterruptedException e) {
                    this.timer = null;
                }
            }
       }
       if (this.timer != null) {
           this.timer.destructor();
       }
    }

    public void stopTimer() {
        this.timerRun = false;
        this.timer.destructor();
        this.interrupt();

    }

}
