package main.controllers.guiControllers;

import main.controllers.TimerController;
import main.support.SupportClass;

import javax.swing.*;
import java.awt.*;

public class GuiTimer extends JFrame {

    private TimerController timerController;
    private JLabel timerLabel;
    private JPanel panel;


    public GuiTimer(TimerController timerController) throws HeadlessException {
        this.timerController = timerController;
        this.setLayout(new GridLayout(0, 1));
        this.setTitle(this.timerController.getTimer().getName());
        this.panel = new JPanel();


        this.timerLabel = new JLabel();
        this.timerLabel.setVisible(true);
        this.updateLabel();
        this.panel.add(this.timerLabel);
        this.panel.setVisible(true);
        this.add(this.panel);

        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }


    public void updateLabel() {
        try {
            long first = this.timerController.getTimer().getTimeTriplet().getFirst();
            long second = this.timerController.getTimer().getTimeTriplet().getSecond();
            long last = this.timerController.getTimer().getTimeTriplet().getLast();

            this.timerLabel.setText(SupportClass.timeToString3(first, second, last));
            this.panel.updateUI();
        } catch (NullPointerException ignored) {

        }
    }


    public TimerController getTimerController() {
        return this.timerController;
    }
}
