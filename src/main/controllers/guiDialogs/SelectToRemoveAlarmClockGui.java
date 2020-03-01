package main.controllers.guiDialogs;

import main.models.clockModels.ClockUniversalModel;
import main.controllers.AlarmClockController;
import main.support.Localisation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SelectToRemoveAlarmClockGui {

    public static void selectAlarmClockDialog(ClockUniversalModel model) {

        JFrame frame = new JFrame(Localisation.selectAlarmClockToRemove());
        List<String> alarmClockList = new ArrayList<>();
        for (AlarmClockController item: model.getAlarmClocksPullController().getControllers()) {
            alarmClockList.add(item.getAlarmClock().getName() + " -> " + item.getAlarmClock().getAlarmDate());
        }

        JPanel panel = new JPanel(new GridLayout(0, 1));
        JComboBox<String> selectCombo = new JComboBox<>();
        selectCombo.setEditable(false);
        for(String string: alarmClockList) selectCombo.addItem(string);

        JPanel buttonPanel = new JPanel();

        JButton removeButton = new JButton(Localisation.removeAlarmClock());
        removeButton.addActionListener(actionEvent -> {
            int num = selectCombo.getSelectedIndex();
            model.getAlarmClocksPullController().getControllers().get(num).stopAlarmClock();
            model.removeAlarmClockController(num);
            model.getAlarmClocksPullController().getControllers().remove(num);
            frame.dispose();
        });
        removeButton.setVisible(true);

        JButton cancelButton = new JButton(Localisation.cancel());
        cancelButton.addActionListener(actionEvent -> frame.dispose());
        cancelButton.setVisible(true);

        buttonPanel.add(removeButton);
        buttonPanel.add(cancelButton);
        buttonPanel.setVisible(true);


        panel.add(selectCombo);
        selectCombo.setVisible(true);
        panel.add(buttonPanel);
        panel.setVisible(true);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

    }
}
