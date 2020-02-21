package models.guiModels;

import controllers.AlarmClockController;
import controllers.AlarmClockGuiCreator;
import models.clockModels.ClockUniversalModel;
import models.supportModels.Triplet;
import myException.TripletBuildException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class GuiClock extends JFrame {

    private static final String tabString = " ".repeat(6);
    private static final String timeSeparator = ":";
    private static final String zeroString = "0";

    private final ClockUniversalModel model;
    private final JPanel panel;
    private List<JLabel> labelList;
    private JButton deleteButton;

    public GuiClock(ClockUniversalModel model) throws HeadlessException, TripletBuildException {
        super(model.getClock().getName());
        this.model = model;
        this.panel = new JPanel(new GridLayout(0, 1));
        this.labelList = new ArrayList<>();

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setVisible(true);
        JButton createButton = createButtonAddAlarmClock();
        this.deleteButton = createButtonRemoveAlarmClock();
        createButton.setVisible(true);
        buttonPanel.add(createButton);
        buttonPanel.add(deleteButton);
        this.panel.add(buttonPanel);

        this.updatePanel();

        this.add(this.panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    private JButton createButtonAddAlarmClock() {
        JButton button = new JButton("+");
        button.addActionListener(actionEvent -> AlarmClockGuiCreator.create(model));
        return button;
    }

    private JButton createButtonRemoveAlarmClock() {
        JButton button = new JButton("-");
        button.addActionListener(actionEvent -> System.out.println("Нажали -"));
        return button;
    }


    private void updateLabel(Triplet<Long> timeTriplet, JLabel label) {
        StringJoiner text = new StringJoiner(timeSeparator);

        if (timeTriplet.getFirst() > 9) text.add(timeTriplet.getFirst().toString());
        else text.add(zeroString + timeTriplet.getFirst());

        if (timeTriplet.getSecond() > 9) text.add(timeTriplet.getSecond().toString());
        else text.add(zeroString + timeTriplet.getSecond());

        if (timeTriplet.getLast() > 9) text.add(timeTriplet.getLast().toString());
        else text.add(zeroString + timeTriplet.getLast());

        label.setText(tabString + text.toString() + tabString);
        label.setFont(SupportClass.FONT1);
    }

    protected void updatePanel() throws TripletBuildException {

        int clockCount = model.howManyElements();

        if (clockCount != this.labelList.size()) {

            for (JLabel label: this.labelList)this.panel.remove(label);

            if (clockCount > this.labelList.size()) {
            this.labelList.add(new JLabel());

            } else {
                while (clockCount < this.labelList.size()) this.labelList.remove(clockCount);
            }

            for (int i = 0; i < clockCount; i++){
                updateLabel(this.model.getTimeElement(i), this.labelList.get(i));
                panel.add(this.labelList.get(i), BorderLayout.NORTH);
            }
        } else {
            for (int i = 0; i < clockCount; i++) updateLabel(this.model.getTimeElement(i), this.labelList.get(i));
        }

        this.deleteButton.setVisible(clockCount > 1);

        this.setSize(SupportClass.calculateWidth(SupportClass.FONT1),
                SupportClass.calculateHeight(this.model, SupportClass.FONT1));
        panel.updateUI();

    }

    public List<AlarmClockController> getControllersList() {
        return this.model.getAlarmClocksPullController().getControllers();
    }

}
