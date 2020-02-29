package main.controllers.consoleControllers;

import main.models.clockModels.AlarmClock;
import main.support.Localisation;

import java.util.Scanner;

public class ConsoleAlarmClockController extends Thread {

    protected AlarmClock alarmClock;
    protected Beeper beeper;

    public ConsoleAlarmClockController(AlarmClock alarmClock) {
        this.alarmClock = alarmClock;
        this.beeper = new Beeper();
        this.beeper.setCountSound(10);
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
        System.out.println(Localisation.addTimeToAlarmClock(alarmClock.getName()));
        int answer = new Scanner(System.in).nextInt();
        beeper.interrupt();
        if (answer == 1) {
            this.alarmClock.appendTime(5 * 60 * 1000);
            System.out.println(Localisation.newTimeToAlarmClock(alarmClock.getName(), alarmClock.getAlarmDate()));
            this.run();
        }
    }


    protected class Beeper extends Thread {

        protected int countSound = 1;


        @Override
        public void run() {
            int countLoop = this.countSound;
            while (countLoop > 0) {
                ConsoleAlarmClockController.this.alarmClock.sayBeep();
                countLoop--;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
            }
        }

        public void setCountSound(int countSound) {
            this.countSound = countSound;
        }
    }
}
