package main.controllers.guiControllers;

import main.support.Localisation;

import javax.swing.*;
import java.awt.*;

public class GuiTimerThread extends Thread {

    private GuiTimer guiTimer;
    private boolean dialogCreated = false;

    public GuiTimerThread(GuiTimer guiTimer) {
        this.guiTimer = guiTimer;
    }

    @Override
    public void run() {
        while (true) {
            if (guiTimer.getTimerController().getTimer().alarmIsRun()) {
                guiTimer.getTimerController().getTimer().sayBeep();
                if (!dialogCreated) {
                    dialogCreated = true;
                    new GuiTimerKiller();

                }
            }

            this.guiTimer.updateLabel();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
               break;
            }
        }
        try {
            this.guiTimer.getTimerController().stopTimer();
        } catch (NullPointerException ignored) {

        }
    }

    private class GuiTimerKiller extends JFrame {

        public GuiTimerKiller() throws HeadlessException {
            this.setLayout(new GridLayout(0, 1));
            this.setTitle(Localisation.alarmMessage());
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);

            JLabel label = new JLabel(Localisation.timerSayBeep(GuiTimerThread.this.guiTimer
                    .getTimerController().getTimer().getName()));
            label.setVisible(true);
            this.add(label);

            JButton cancelButton = new JButton(Localisation.stopTimerSignal());
            cancelButton.addActionListener(actionEvent -> {
                GuiTimerThread.this.guiTimer.getTimerController().stopTimer();
                GuiTimerThread.this.interrupt();
                GuiTimerThread.this.guiTimer.dispose();
                GuiTimerThread.this.guiTimer = null;
                System.exit(0);
            });
            cancelButton.setVisible(true);
            this.add(cancelButton);

            this.pack();
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        }
    }
}
