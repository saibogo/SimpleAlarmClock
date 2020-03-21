package main.controllers.guiControllers;

import main.controllers.GuiStarter;
import main.support.Localisation;
import main.support.SupportClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GuiTimerThread extends Thread {

    private GuiTimer guiTimer;
    private boolean dialogCreated = false;

    public GuiTimerThread(GuiTimer guiTimer) {
        this.guiTimer = guiTimer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (guiTimer.getTimerController().getTimer().alarmIsRun()) {
                    guiTimer.getTimerController().getTimer().sayBeep();
                    if (!dialogCreated) {
                        dialogCreated = true;
                        new GuiTimerKiller();

                    }
                }
                if (this.guiTimer.getTimerController() != null) this.guiTimer.updateLabel();

            } catch (NullPointerException ignored) {

            }
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
            this.setIconImage(SupportClass.getImageIcon());

            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    closingWindow();
                }
            });

            JLabel label = new JLabel(Localisation.timerSayBeep(GuiTimerThread.this.guiTimer
                    .getTimerController().getTimer().getName()));
            label.setVisible(true);
            this.add(label);

            JButton cancelButton = new JButton(Localisation.stopTimerSignal());
            cancelButton.addActionListener(actionEvent -> closingWindow());
            cancelButton.setVisible(true);
            this.add(cancelButton);

            this.pack();
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        }

        private void closingWindow() {
            GuiTimerThread.this.guiTimer.getTimerController().stopTimer();
            GuiTimerThread.this.interrupt();
            GuiTimerThread.this.guiTimer.dispose();
            guiTimer.dispose();
            GuiTimerThread.this.guiTimer = null;
            dispose();
            GuiStarter.startGui();
        }
    }
}
