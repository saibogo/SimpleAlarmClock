package main.controllers.guiDialogs;

import main.models.clockModels.ClockUniversalModel;
import main.models.clockModels.AlarmClock;
import main.support.InstrumentLocalisation;
import main.support.Localisation;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class AlarmClockGuiCreator {

    public static void create(ClockUniversalModel clockUniversalModel) {

        JFrame frame = new JFrame(Localisation.addAlarmClock());
        frame.setLayout(new GridLayout(0, 2));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        frame.add(new JLabel(Localisation.devicesName()));
        JTextField fieldName = new JTextField(15);
        fieldName.setText(Localisation.alarmClock());
        frame.add(fieldName);

        frame.add(new JLabel(Localisation.setupTime()));
        SpinnerDateModel model = new SpinnerDateModel();
        long addMsToAlarm = 300000L;
        model.setValue(new Date(System.currentTimeMillis() + addMsToAlarm));
        JSpinner spinner = new JSpinner(model);
        frame.add(spinner);

        JLabel volumeLabel = new JLabel(Localisation.volume());
        frame.add(volumeLabel);
        JSlider volume = new JSlider();
        volume.setMaximum(100);
        volume.setMinimum(0);
        volume.setValue(50);
        volume.addChangeListener(changeEvent -> volumeLabel.setText(volume.getValue() + "%"));
        frame.add(volume);

        JLabel noteLabel = new JLabel(Localisation.noteInstrument());
        frame.add(noteLabel);
        JSlider note = new JSlider();
        note.setMinimum(0);
        note.setMaximum(131);
        note.setValue(43);
        note.addChangeListener(changeEvent -> noteLabel.setText(Localisation.noteName(note.getValue())));
        frame.add(note);

        JLabel instrumentLabel = new JLabel(Localisation.instrumentNumber());
        frame.add(instrumentLabel);
        JSlider instrument = new JSlider();
        instrument.setMinimum(1);
        instrument.setMaximum(InstrumentLocalisation.getMaximalInstrumentNumber());
        instrument.setValue(47);
        instrument.addChangeListener(changeEvent -> instrumentLabel.setText(Localisation
                .nameInstrument(instrument.getValue())));
        frame.add(instrument);

        JButton createButton = new JButton(Localisation.create());
        createButton.addActionListener(actionEvent -> {
            if (model.getDate().getTime() > System.currentTimeMillis()) {

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
                        Localisation.timeTooSmall(),
                        Localisation.notCorrectedTime(), JOptionPane.DEFAULT_OPTION);
            }
        });
        frame.add(createButton);

        JButton cancelButton = new JButton(Localisation.cancel());
        cancelButton.addActionListener(actionEvent -> frame.dispose());
        frame.add(cancelButton);

        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

    }
}
