package controllers;

import models.clockModels.AlarmClock;
import models.clockModels.ClockUniversalModel;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class AlarmClockGuiCreator {

    public static void create(ClockUniversalModel clockUniversalModel) {
        JFrame frame = new JFrame("Новый будильник");
        frame.setLayout(new GridLayout(0, 2));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.add(new JLabel("Имя будильника:"));
        JTextField fieldName = new JTextField(15);
        fieldName.setText("Будильник");
        frame.add(fieldName);

        frame.add(new JLabel("Время включения:"));
        SpinnerDateModel model = new SpinnerDateModel();
        model.setValue(new Date(new Date().getTime() + 100000));
        JSpinner spinner = new JSpinner(model);
        frame.add(spinner);

        frame.add(new JLabel("Громкость:"));
        JSlider volume = new JSlider();
        volume.setMaximum(100);
        volume.setMinimum(0);
        volume.setValue(50);
        frame.add(volume);

        frame.add(new JLabel("Нота сигнала:"));
        JSlider note = new JSlider();
        note.setMinimum(0);
        note.setMaximum(131);
        note.setValue(43);
        frame.add(note);

        frame.add(new JLabel("Инструмент сигнала:"));
        JSlider instrument = new JSlider();
        instrument.setMinimum(1);
        instrument.setMaximum(128);
        instrument.setValue(47);
        frame.add(instrument);

        JButton createButton = new JButton("Создать");
        createButton.addActionListener(actionEvent -> {
            if (model.getDate().getTime() > (new Date()).getTime()) {

                AlarmClock ac = new AlarmClock.Builder()
                        .setName(fieldName.getText())
                        .setAlarmDate(model.getDate())
                        .setVolume(volume.getValue())
                        .setNote(note.getValue())
                        .setInstrument(instrument.getValue())
                        .build();
                clockUniversalModel.getAlarmClocksPullController().addAlarmClock(ac);
                frame.dispose();
            } else {
                JOptionPane.showConfirmDialog(new Frame(),
                        "Дата и время должны быть больше текущего!",
                        "Некорректное время", JOptionPane.DEFAULT_OPTION);
            }
        });
        frame.add(createButton);

        JButton cancelButton = new JButton("Отмена");
        cancelButton.addActionListener(actionEvent -> frame.dispose());
        frame.add(cancelButton);

        int WIDTH = 300;
        int HEIGHT = 200;
        frame.setSize(WIDTH, HEIGHT);                // задаем размеры окна
        frame.setResizable(false);              // запрещаем менять размер окна
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

    }
}
