package main.controllers.guiDialogs;

import main.controllers.StopWatchController;
import main.controllers.guiControllers.GuiStopWatchThread;
import main.models.clockModels.StopWatch;
import main.models.supportModels.GuiCreatorModel;
import main.support.DevicesType;
import main.support.Localisation;

import javax.swing.*;
import java.awt.*;

public class MainSelectedMenu extends JFrame {

    public MainSelectedMenu() {
        this.setLayout(new GridLayout(0, 1));
        this.setTitle(Localisation.selectDevice());

        JButton alarmClockButton = new JButton(Localisation.alarmClock());
        alarmClockButton.addActionListener(actionEvent -> {
            MainSelectedMenu.this.dispose();
            new GuiCreatorController(Localisation.alarmClock());
        });
        alarmClockButton.setVisible(true);

        JButton timerButton = new JButton(Localisation.timer());
        timerButton.setVisible(true);
        timerButton.addActionListener(actionEvent -> {
            MainSelectedMenu.this.dispose();
            new GuiCreatorModel(DevicesType.TIMER);
        });

        JButton stopWatchButton = new JButton(Localisation.stopWatch());
        stopWatchButton.setVisible(true);
        stopWatchButton.addActionListener(actionEvent -> {
            StopWatch stopWatch = new StopWatch();
            StopWatchController controller = new StopWatchController(stopWatch);
            new GuiStopWatchThread(controller).start();
            dispose();
        });

        JButton infoButton = new JButton(Localisation.information());
        infoButton.setVisible(true);
        infoButton.addActionListener(actionEvent -> GuiInformation.showProductionInfo());

        JButton changeLanguageButton = new JButton(Localisation.changeLanguage());
        changeLanguageButton.setVisible(true);
        changeLanguageButton.addActionListener(actionEvent -> {
            if (Localisation.getCurrentLanguage() == Localisation.Languages.RU) {
                Localisation.setCurrentLang(Localisation.Languages.ENG);
            } else {
                Localisation.setCurrentLang(Localisation.Languages.RU);
            }
            this.setTitle(Localisation.selectDevice());
            alarmClockButton.setText(Localisation.alarmClock());
            timerButton.setText(Localisation.timer());
            stopWatchButton.setText(Localisation.stopWatch());
            infoButton.setText(Localisation.information());
            changeLanguageButton.setText(Localisation.changeLanguage());
            this.pack();
        });

        this.add(alarmClockButton);
        this.add(timerButton);
        this.add(stopWatchButton);
        this.add(infoButton);
        this.add(changeLanguageButton);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
