package main.controllers.guiDialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainSelectedMenu extends JFrame {

    public MainSelectedMenu() {
        this.setLayout(new GridLayout(0, 1));
        this.setTitle("Что использовать?");

        JButton alarmClockButton = new JButton("Часы с будильником");
        alarmClockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainSelectedMenu.this.dispose();
                new GuiCreatorController("Будильник");
            }
        });
        alarmClockButton.setVisible(true);

        JButton timerButton = new JButton("Простой таймер");
        timerButton.setVisible(true);
        timerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainSelectedMenu.this.dispose();
                new GuiCreatorTimer();
            }
        });

        this.add(alarmClockButton);
        this.add(timerButton);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
