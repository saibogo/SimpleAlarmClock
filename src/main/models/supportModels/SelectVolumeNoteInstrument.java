package main.models.supportModels;

import main.support.InstrumentLocalisation;
import main.support.Localisation;

import javax.swing.*;
import java.awt.*;

public class SelectVolumeNoteInstrument {

    private JPanel panel = new JPanel(new GridLayout(3,2));
    private ExtendedJSlider volume;
    private ExtendedJSlider note;
    private ExtendedJSlider instrument;

    public SelectVolumeNoteInstrument(){
        JLabel volumeLabel = new JLabel(Localisation.volume());
        panel.add(volumeLabel);
        this.volume = new ExtendedJSlider.Builder()
                .setMaximum(100)
                .setCurrentValue(50)
                .setMinimum(0)
                .build();
        volume.addChangeListener(changeEvent -> volumeLabel.setText(volume.getValue() + "%"));
        panel.add(volume.toJPanel());

        JLabel noteLabel = new JLabel(Localisation.noteInstrument());
        panel.add(noteLabel);
        this.note = new ExtendedJSlider.Builder()
                .setMaximum(131)
                .setMinimum(0)
                .setCurrentValue(43)
                .build();
        note.addChangeListener(changeEvent -> noteLabel.setText(Localisation.noteName(note.getValue())));
        panel.add(note.toJPanel());

        JLabel instrumentLabel = new JLabel(Localisation.instrumentNumber());
        panel.add(instrumentLabel);
        this.instrument = new ExtendedJSlider.Builder()
                .setMaximum(InstrumentLocalisation.getMaximalInstrumentNumber())
                .setMinimum(1)
                .setCurrentValue(47)
                .build();
        instrument.addChangeListener(changeEvent -> instrumentLabel.setText(Localisation
                .nameInstrument(instrument.getValue())));
        panel.add(instrument.toJPanel());
    }

    public void setVisible(boolean status){
        this.panel.setVisible(status);
    }

    public JPanel toJPanel() {
        return this.panel;
    }

    public int getVolume() {
        return this.volume.getValue();
    }

    public int getNote() {
        return this.note.getValue();
    }

    public int getInstrument() {
        return this.instrument.getValue();
    }

}
