package main.controllers;

import main.clockModels.AlarmClock;
import main.clockModels.AlarmClocksPull;
import main.controllers.AlarmClockController;
import main.myException.AlarmExistsException;

import java.util.ArrayList;
import java.util.List;

public class AlarmClocksPullController extends Thread{

    private final AlarmClocksPull alarmClocksPull;
    private final List<AlarmClockController> alarmClockControllerList;
    private boolean exit = false;

    public AlarmClocksPullController(AlarmClocksPull alarmClocksPull) {
        this.alarmClocksPull = alarmClocksPull;
        this.alarmClockControllerList = new ArrayList<>();
    }

    public void addAlarmClock(final AlarmClock alarmClock) {

        try {
            this.alarmClocksPull.addAlarmClock(alarmClock);
            AlarmClockController newController = new AlarmClockController(alarmClock);

            this.alarmClockControllerList.add(newController);
            alarmClockControllerList.get(alarmClockControllerList.size() - 1).start();

        } catch (AlarmExistsException e) {
            System.out.println("Not add " + alarmClock + " in pull");
        }

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

    public AlarmClocksPull getAlarmClocks() { return this.alarmClocksPull; }



    @Override
    public void run() {
        try {
            while (!exit) {
                removeNotRunning();
                int TIME_INTERVAL = 1000;
                Thread.sleep(TIME_INTERVAL);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void interrupt() {
        this.exit = true;
    }
}
