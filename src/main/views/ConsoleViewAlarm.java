package main.views;

import main.models.clockModels.AlarmClock;

public class ConsoleViewAlarm {
    private final AlarmClock alarmClock;

    public ConsoleViewAlarm(final AlarmClock alarmClock) {
        this.alarmClock = alarmClock;
    }

    public void show() {
        try {
            if (!alarmClock.alarmIsRun()) {
                System.out.println("Seconds left(" + this.alarmClock.getName() + "): "  +
                        this.alarmClock.secondsLeft());
            } else {
                System.out.println("Alarm Clock " + this.alarmClock.getName() + " say beep!");
            }
        } catch (NullPointerException e) {
            System.out.println("Alarm Clock " + this.alarmClock.getName() + " destroyed!");
        }
    }
}
