package controllers;

import javax.swing.*;
import java.awt.*;

public class AlarmClockGuiKiller extends Thread {

    private final AlarmClockController controller;

    public AlarmClockGuiKiller(AlarmClockController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        int n = JOptionPane.showConfirmDialog(new Frame(), "Отложить будильник " +
                this.controller.getAlarmClock().getName() + "?", "Оповещение", JOptionPane.YES_NO_OPTION);

        if (n == JOptionPane.YES_OPTION) {
            //in milliseconds
            long DELTA_TIME = 5 * 60 * 1000;
            this.controller.getAlarmClock().appendTime(DELTA_TIME);
            this.controller.getAlarmClock().setNotCreatedDialog(false);
        } else {
            this.controller.stopAlarmClock();
        }
    }
}
