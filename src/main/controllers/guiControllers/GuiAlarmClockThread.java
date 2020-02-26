package main.controllers.guiControllers;

import main.controllers.AlarmClockController;
import main.controllers.guiDialogs.AlarmClockGuiKiller;
import main.myException.TripletBuildException;

import java.util.List;

public class GuiAlarmClockThread extends Thread {

    private final GuiClock guiClock;

    public GuiAlarmClockThread(GuiClock guiClock) {
        this.guiClock = guiClock;
    }

    @Override
    public void run() {
        List<AlarmClockController> tmp;
        while (true) {
            try {
                this.guiClock.updatePanel();
                tmp = guiClock.getControllersList();
                for (AlarmClockController item: tmp) {
                    if (item.getAlarmClock().alarmIsRun() && !item.getAlarmClock().isNotCreatedDialog()) {
                        item.getAlarmClock().setNotCreatedDialog(true);
                        (new AlarmClockGuiKiller(item)).start();
                    }
                }

            } catch (TripletBuildException e) {
                e.printStackTrace();
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
