package main.controllers.guiControllers;

import main.controllers.TimerController;

import javax.swing.*;
import java.awt.*;
import java.util.StringJoiner;

public class GuiTimer extends JFrame {

    private TimerController timerController;
    private JLabel timerLabel;
    private JPanel panel;

    private static final String timeSeparator = ":";

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
        StringJoiner text = new StringJoiner(timeSeparator);
        long first = this.timerController.getTimer().getTimeTriplet().getFirst();
        long second = this.timerController.getTimer().getTimeTriplet().getSecond();
        long last = this.timerController.getTimer().getTimeTriplet().getLast();


        text.add(first > 9 ? " " + first : " 0" + first);
        text.add(second > 9 ? "" + second : "0" + second);
        text.add(last > 9 ? "" + last  + " " : "0" + last + " ");

        this.timerLabel.setText(text.toString());
        this.panel.updateUI();
    }


    public TimerController getTimerController() {
        return this.timerController;
    }
}
