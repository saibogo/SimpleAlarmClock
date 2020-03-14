package main.models.supportModels;

import main.support.DevicesType;
import main.support.Localisation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonCreateCancelPanel {

    private static int columns = 5;
    private JPanel panel = new JPanel(new GridLayout(0, columns));
    private JButton createButton;
    private JButton cancelButton;

    public ButtonCreateCancelPanel(DevicesType devicesType) {
        createButton =
                new JButton(devicesType == DevicesType.TIMER ? Localisation.startTimer() : Localisation.create());
        cancelButton = new JButton(Localisation.cancel());
        for (int i = 0; i <= columns; i++) this.addEmptyLabel();

        this.panel.add(createButton);
        this.addEmptyLabel();
        this.panel.add(cancelButton);

        for (int i = 0; i <= columns; i++) this.addEmptyLabel();
    }

    public void setVisible(boolean status) {
        this.panel.setVisible(status);
    }

    public JPanel toJPanel() {
        return this.panel;
    }

    private void addEmptyLabel() {
        this.panel.add(new JLabel());
    }

    public void addListenerToCreateButton(ActionListener listener) {
        this.createButton.addActionListener(listener);
    }

    public void addListenerToCancelButton(ActionListener listener) {
        this.cancelButton.addActionListener(listener);
    }

}

