package main.controllers.guiDialogs;

import main.controllers.StopWatchController;
import main.controllers.guiControllers.GuiStopWatch;
import main.controllers.guiControllers.GuiStopWatchThread;
import main.models.clockModels.StopWatch;

import javax.swing.*;
import java.awt.*;

public class MainSelectedMenu extends JFrame {

    public MainSelectedMenu() {
        this.setLayout(new GridLayout(0, 1));
        this.setTitle("Что использовать?");

        JButton alarmClockButton = new JButton("Часы с будильником");
        alarmClockButton.addActionListener(actionEvent -> {
            MainSelectedMenu.this.dispose();
            new GuiCreatorController("Будильник");
        });
        alarmClockButton.setVisible(true);

        JButton timerButton = new JButton("Простой таймер");
        timerButton.setVisible(true);
        timerButton.addActionListener(actionEvent -> {
            MainSelectedMenu.this.dispose();
            new GuiCreatorTimer();
        });

        JButton stopWatchButton = new JButton("Секундомер");
        stopWatchButton.setVisible(true);
        stopWatchButton.addActionListener(actionEvent -> {
            StopWatch stopWatch = new StopWatch();
            StopWatchController controller = new StopWatchController(stopWatch);
            new GuiStopWatchThread(controller).start();
            dispose();
        });

        JButton infoButton = new JButton("О программе");
        infoButton.setVisible(true);
        infoButton.addActionListener(actionEvent -> GuiInformation.showProductionInfo());

        this.add(alarmClockButton);
        this.add(timerButton);
        this.add(stopWatchButton);
        this.add(infoButton);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
