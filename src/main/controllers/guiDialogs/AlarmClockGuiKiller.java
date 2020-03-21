package main.controllers.guiDialogs;

import main.controllers.AlarmClockController;
import main.support.Localisation;
import main.support.SupportClass;

import javax.swing.*;

public class AlarmClockGuiKiller extends Thread {

    private AlarmClockController controller;

    public AlarmClockGuiKiller(AlarmClockController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        JFrame message = new JFrame();
        message.setIconImage(SupportClass.getImageIcon());
        int n = JOptionPane.showConfirmDialog(message,
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
