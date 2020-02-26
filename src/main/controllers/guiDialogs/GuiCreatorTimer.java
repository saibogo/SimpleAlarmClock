package main.controllers.guiDialogs;

import main.controllers.TimerController;
import main.controllers.guiControllers.GuiTimer;
import main.controllers.guiControllers.GuiTimerThread;
import main.models.clockModels.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class GuiCreatorTimer extends JFrame {

    public GuiCreatorTimer() throws HeadlessException {

        this.setLayout(new GridLayout(0, 2));
        this.setTitle("Параметры таймера");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel labelName =  new JLabel("Имя таймера");
        labelName.setVisible(true);
        JTextField fieldName = new JTextField(15);
        fieldName.setText("Таймер");
        fieldName.setVisible(true);
        this.add(labelName);
        this.add(fieldName);

        JLabel hours = new JLabel("Часов");
        hours.setVisible(true);
        JSpinner hoursSpinner = new JSpinner();
        SpinnerNumberModel hoursModel = new SpinnerNumberModel();
        hoursModel.setMinimum(0);
        hoursModel.setMaximum(99);
        hoursSpinner.setModel(hoursModel);
        hoursSpinner.setVisible(true);
        this.add(hours);
        this.add(hoursSpinner);

        JLabel minutes = new JLabel("Минут");
        minutes.setVisible(true);
        JSpinner minutesSpinner = new JSpinner();
        SpinnerNumberModel minutesModel = new SpinnerNumberModel();
        minutesModel.setMinimum(0);
        minutesModel.setMaximum(1000);
        minutesSpinner.setModel(minutesModel);
        minutesSpinner.setVisible(true);
        this.add(minutes);
        this.add(minutesSpinner);

        JLabel seconds = new JLabel("Секунд");
        seconds.setVisible(true);
        JSpinner secondsSpinner = new JSpinner();
        SpinnerNumberModel secondsModel = new SpinnerNumberModel();
        secondsModel.setMinimum(0);
        secondsModel.setMaximum(1000);
        secondsSpinner.setModel(secondsModel);
        secondsSpinner.setVisible(true);
        this.add(seconds);
        this.add(secondsSpinner);

        this.add(new JLabel("Громкость:"));
        JSlider volume = new JSlider();
        volume.setMaximum(100);
        volume.setMinimum(0);
        volume.setValue(50);
        this.add(volume);

        this.add(new JLabel("Нота сигнала:"));
        JSlider note = new JSlider();
        note.setMinimum(0);
        note.setMaximum(131);
        note.setValue(43);
        this.add(note);

        this.add(new JLabel("Инструмент сигнала:"));
        JSlider instrument = new JSlider();
        instrument.setMinimum(1);
        instrument.setMaximum(128);
        instrument.setValue(47);
        this.add(instrument);


        JButton createButton = new JButton("Запустить таймер");
        createButton.setVisible(true);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                long deltaTime = (int)hoursModel.getNumber() * 3600 +
                        (int)minutesModel.getNumber() * 60 +
                        (int)secondsModel.getNumber();
                Timer timer = (Timer) new Timer.Builder()
                        .setName(fieldName.getText())
                        .setAlarmDate(new Date(Calendar.getInstance().getTimeInMillis() + deltaTime * 1000))
                        .setVolume(volume.getValue())
                        .setNote(note.getValue())
                        .setInstrument(instrument.getValue())
                        .build();
                TimerController controller = new TimerController(timer);
                GuiTimer guiTimer = new GuiTimer(controller);
                controller.start();
                GuiTimerThread guiTimerThread = new GuiTimerThread(guiTimer);
                guiTimerThread.start();
                dispose();
            }
        });
        JButton cancelButton = new JButton("Отмена");
        cancelButton.setVisible(true);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        this.add(createButton);
        this.add(cancelButton);


        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
}
