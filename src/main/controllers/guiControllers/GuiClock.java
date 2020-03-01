package main.controllers.guiControllers;

import main.controllers.AlarmClockController;
import main.controllers.guiDialogs.AlarmClockGuiCreator;
import main.models.clockModels.ClockUniversalModel;
import main.controllers.guiDialogs.SelectToRemoveAlarmClockGui;
import main.models.supportModels.Triplet;
import main.myException.TripletBuildException;
import main.support.Localisation;
import main.support.SupportClass;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GuiClock extends JFrame {

    private final ClockUniversalModel model;
    private final JPanel panel;
    private List<JLabel> labelList;
    private JButton deleteButton;


    public GuiClock(ClockUniversalModel model) throws HeadlessException, TripletBuildException {
        super(model.getClock().getName());
        this.model = model;
        this.panel = new JPanel(new GridLayout(0, 1));
        this.labelList = new ArrayList<>();
        this.setTitle(Localisation.alarmClock());

        JPanel buttonPanel = new JPanel(new GridLayout(0, 2));
        buttonPanel.setVisible(true);

        JButton createButton = createButtonAddAlarmClock();
        this.deleteButton = createButtonRemoveAlarmClock();

        createButton.setVisible(true);
        buttonPanel.add(createButton);
        buttonPanel.add(deleteButton);

        this.panel.add(buttonPanel);

        this.updatePanel();
        this.labelList.get(0).setForeground(Color.BLUE);

        this.add(this.panel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    private JButton createButtonAddAlarmClock() {
        JButton button = new JButton("+");
        button.addActionListener(actionEvent -> AlarmClockGuiCreator.create(model));
        button.setToolTipText(Localisation.addAlarmClock());
        return button;
    }

    private JButton createButtonRemoveAlarmClock() {
        JButton button = new JButton("-");
        button.addActionListener(actionEvent -> SelectToRemoveAlarmClockGui.selectAlarmClockDialog(model));
        button.setToolTipText(Localisation.removeAlarmClock());
        return button;
    }


    private void updateLabel(Triplet<Long> timeTriplet, JLabel label) {

        label.setText(SupportClass.timeToString3(timeTriplet.getFirst(),
                timeTriplet.getSecond(), timeTriplet.getLast()));
    }

    protected void updatePanel() throws TripletBuildException {

        int clockCount = model.howManyElements();

        if (clockCount != this.labelList.size()) {

            for (JLabel label: this.labelList)this.panel.remove(label);

            if (clockCount > this.labelList.size()) {
            this.labelList.add(new JLabel());
            this.labelList.get(this.labelList.size() - 1).setForeground(Color.PINK);

            } else {
                while (clockCount < this.labelList.size()) this.labelList.remove(clockCount);
            }

            for (int i = 0; i < clockCount; i++){
                updateLabel(this.model.getTimeElement(i), this.labelList.get(i));
                panel.add(this.labelList.get(i));
            }
        } else {
            for (int i = 0; i < clockCount; i++) updateLabel(this.model.getTimeElement(i), this.labelList.get(i));
        }

        this.deleteButton.setVisible(clockCount > 1);

        panel.updateUI();
        this.pack();

    }

    public List<AlarmClockController> getControllersList() {
        return this.model.getAlarmClocksPullController().getControllers();
    }

}
