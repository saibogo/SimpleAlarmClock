package main.controllers.guiDialogs;

import main.controllers.TimerController;
import main.controllers.guiControllers.GuiTimer;
import main.controllers.guiControllers.GuiTimerThread;
import main.models.clockModels.Timer;
import main.support.InstrumentLocalisation;
import main.support.Localisation;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class GuiCreatorTimer extends JFrame {

    private static long msInSecond = 1000L;
    private static long secInMinutes = 60L;
    private static long secInHour = 60 * secInMinutes;


    public GuiCreatorTimer() throws HeadlessException {

        this.setLayout(new GridLayout(0, 2));
        this.setTitle(Localisation.timerParameters());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel labelName =  new JLabel(Localisation.devicesName());
        labelName.setVisible(true);
        JTextField fieldName = new JTextField(15);
        fieldName.setText(Localisation.timer());
        fieldName.setVisible(true);
        this.add(labelName);
        this.add(fieldName);

        JLabel hours = new JLabel(Localisation.hours());
        hours.setVisible(true);
        JSpinner hoursSpinner = new JSpinner();
        SpinnerNumberModel hoursModel = new SpinnerNumberModel();
        hoursModel.setMinimum(0);
        hoursModel.setMaximum(99);
        hoursSpinner.setModel(hoursModel);
        hoursSpinner.setVisible(true);
        this.add(hours);
        this.add(hoursSpinner);

        JLabel minutes = new JLabel(Localisation.minutes());
        minutes.setVisible(true);
        JSpinner minutesSpinner = new JSpinner();
        SpinnerNumberModel minutesModel = new SpinnerNumberModel();
        minutesModel.setMinimum(0);
        minutesModel.setMaximum(1000);
        minutesSpinner.setModel(minutesModel);
        minutesSpinner.setVisible(true);
        this.add(minutes);
        this.add(minutesSpinner);

        JLabel seconds = new JLabel(Localisation.seconds());
        seconds.setVisible(true);
        JSpinner secondsSpinner = new JSpinner();
        SpinnerNumberModel secondsModel = new SpinnerNumberModel();
        secondsModel.setMinimum(0);
        secondsModel.setMaximum(1000);
        secondsSpinner.setModel(secondsModel);
        secondsSpinner.setVisible(true);
        this.add(seconds);
        this.add(secondsSpinner);

        JLabel volumeLabel = new JLabel(Localisation.volume());
        this.add(volumeLabel);
        JSlider volume = new JSlider();
        volume.setMaximum(100);
        volume.setMinimum(0);
        volume.setValue(50);
        volume.addChangeListener(changeEvent -> volumeLabel.setText(volume.getValue() + "%"));
        this.add(volume);

        JLabel noteLabel = new JLabel(Localisation.noteInstrument());
        this.add(noteLabel);
        JSlider note = new JSlider();
        note.setMinimum(0);
        note.setMaximum(131);
        note.setValue(43);
        note.addChangeListener(changeEvent -> noteLabel.setText(Localisation.noteName(note.getValue())));
        this.add(note);

        JLabel instrumentLabel = new JLabel(Localisation.instrumentNumber());
        this.add(instrumentLabel);
        JSlider instrument = new JSlider();
        instrument.setMinimum(1);
        instrument.setMaximum(InstrumentLocalisation.getMaximalInstrumentNumber());
        instrument.setValue(47);
        instrument.addChangeListener(changeEvent -> instrumentLabel
                .setText(Localisation.nameInstrument(instrument.getValue())));
        this.add(instrument);


        JButton createButton = new JButton(Localisation.startTimer());
        createButton.setVisible(true);
        createButton.addActionListener(actionEvent -> {
            long deltaTime = (int)hoursModel.getNumber() * secInHour +
                    (int)minutesModel.getNumber() * secInMinutes +
                    (int)secondsModel.getNumber();
            Timer timer = (Timer) new Timer.Builder()
                    .setName(fieldName.getText())
                    .setAlarmDate(new Date(System.currentTimeMillis() + deltaTime * msInSecond))
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
        });
        JButton cancelButton = new JButton(Localisation.cancel());
        cancelButton.setVisible(true);
        cancelButton.addActionListener(actionEvent -> System.exit(0));

        this.add(createButton);
        this.add(cancelButton);


        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
}
