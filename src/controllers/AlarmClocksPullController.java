package controllers;

import models.AlarmClock;
import models.AlarmClocksPull;
import models.AlarmExistsException;

import java.util.ArrayList;
import java.util.List;

public class AlarmClocksPullController extends Thread{

    private static int TIME_INTERVAL = 1000;

    private AlarmClocksPull alarmClocksPull;
    private List<AlarmClockController> alarmClockControllerList;
    private boolean exit = false;

    public AlarmClocksPullController(AlarmClocksPull alarmClocksPull) {
        this.alarmClocksPull = alarmClocksPull;
        this.alarmClockControllerList = new ArrayList<>();
    }

    public boolean addAlarmClock(final AlarmClock alarmClock) {

        try {
            this.alarmClocksPull.addAlarmClock(alarmClock);
            AlarmClockController newController = new AlarmClockController(alarmClock);

            this.alarmClockControllerList.add(newController);
            alarmClockControllerList.get(alarmClockControllerList.size() - 1).start();

        } catch (AlarmExistsException e) {
            System.out.println("Not add " + alarmClock + " in pull");
            return false;
        }

        return true;
    }

    public boolean pullIsEmpty() {
        return this.alarmClockControllerList.isEmpty();
    }

    public void removeNotRunning() {

        for (AlarmClockController alarmClockController: this.alarmClockControllerList) {
            if (!alarmClockController.isAlive()) {
                this.alarmClocksPull.removeAlarmClock(alarmClockController.getAlarmClock());
                this.alarmClockControllerList.remove(alarmClockController);
                break;
            }
        }
    }

    public int size() {
        return this.alarmClocksPull.size();
    }


    public List<AlarmClockController> getControllers() {
        return this.alarmClockControllerList;
    }



    @Override
    public void run() {
        try {
            while (!exit) {
                removeNotRunning();
                Thread.sleep(TIME_INTERVAL);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    @Override
    public void interrupt() {
        this.exit = true;
    }
}
