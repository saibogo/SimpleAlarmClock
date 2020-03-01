package main.controllers.guiDialogs;

import main.models.clockModels.ClockUniversalModel;
import main.models.clockModels.AlarmClock;
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

        frame.add(new JLabel(Localisation.volume()));
        JSlider volume = new JSlider();
        volume.setMaximum(100);
        volume.setMinimum(0);
        volume.setValue(50);
        frame.add(volume);

        frame.add(new JLabel(Localisation.noteInstrument()));
        JSlider note = new JSlider();
        note.setMinimum(0);
        note.setMaximum(131);
        note.setValue(43);
        frame.add(note);

        frame.add(new JLabel(Localisation.instrumentNumber()));
        JSlider instrument = new JSlider();
        instrument.setMinimum(1);
        instrument.setMaximum(128);
        instrument.setValue(47);
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
