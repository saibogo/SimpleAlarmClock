package main.controllers.guiDialogs;

import main.controllers.AlarmClockController;
import main.support.Localisation;

import javax.swing.*;
import java.awt.*;

public class AlarmClockGuiKiller extends Thread {

    private AlarmClockController controller;

    public AlarmClockGuiKiller(AlarmClockController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        int n = JOptionPane.showConfirmDialog(new Frame(),
                Localisation.addTimeToAlarmClockGui(this.controller.getAlarmClock().getName()),
                Localisation.alarmMessage(), JOptionPane.YES_NO_OPTION);

        if (n == JOptionPane.YES_OPTION) {
            long DELTA_TIME = 5 * 60 * 1000;
            this.controller.getAlarmClock().appendTime(DELTA_TIME);
            this.controller.getAlarmClock().setNotCreatedDialog(false);
        } else {
            this.controller.stopAlarmClock();
            this.controller = null;
        }
    }
}
