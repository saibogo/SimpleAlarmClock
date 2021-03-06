package main.controllers.consoleControllers;

import main.models.clockModels.Timer;
import main.support.Localisation;


public class ConsoleTimerController extends ConsoleAlarmClockController {


    public ConsoleTimerController(Timer timer) {
        super(timer);
        this.beeper.setCountSound(100);
    }

    @Override
    public void run() {
        while (!this.alarmClock.alarmIsRun()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        beeper.start();
        System.out.println(Localisation.timerSayBeep(this.alarmClock.getName()));
        System.out.println(Localisation.pleaseInterrupt());
    }
}
