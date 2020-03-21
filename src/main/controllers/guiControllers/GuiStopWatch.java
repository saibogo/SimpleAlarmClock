package main.controllers.guiControllers;

import main.controllers.GuiStarter;
import main.controllers.StopWatchController;
import main.support.Localisation;
import main.support.SupportClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GuiStopWatch extends JFrame {

    private StopWatchController controller;
    private JLabel labelWatch;
    private boolean isRunning;

    public GuiStopWatch(StopWatchController controller) throws HeadlessException {
        this.controller = controller;
        this.isRunning = true;
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                controller.stopStopWatch();
                GuiStarter.startGui();
            }
        });
        this.setTitle(Localisation.stopWatch());
        this.setIconImage(SupportClass.getImageIcon());
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(0, 1));

        JButton stopButton = new JButton(Localisation.stopString());
        stopButton.setVisible(true);
        stopButton.addActionListener(actionEvent -> {
            this.isRunning = false;
            this.updateLabel();
            JFrame messageFrame = new JFrame();
            messageFrame.setIconImage(SupportClass.getImageIcon());
            String msg = Localisation.stopWatchTimeScaled() + ":\n" + this.labelWatch.getText();
            JOptionPane.showMessageDialog(messageFrame, msg, Localisation.stopWatchValues(),
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
            System.gc();
            GuiStarter.startGui();
        });

        this.labelWatch = new JLabel();
        this.updateLabel();
        this.labelWatch.setVisible(true);
        this.add(this.labelWatch);
        this.add(stopButton);
        this.pack();
        this.setVisible(true);
    }

    public void updateLabel() {
        this.controller.getStopWatch().updateTimePassed();

        this.labelWatch.setText(SupportClass.timeToString4(controller.getStopWatch().getHoursPassed(),
                controller.getStopWatch().getMinutesPassed(),
                controller.getStopWatch().getSecondsPassed(),
                controller.getStopWatch().getTenthPassed()));
        this.pack();
    }

    public boolean isRunning() {
        return isRunning;
    }
}
