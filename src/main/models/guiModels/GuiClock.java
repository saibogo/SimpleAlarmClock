package main.models.guiModels;

import main.controllers.AlarmClockController;
import main.controllers.AlarmClockGuiCreator;
import main.clockModels.ClockUniversalModel;
import main.controllers.AlarmClockGuiRemove;
import main.models.supportModels.Triplet;
import main.myException.TripletBuildException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class GuiClock extends JFrame {

    private static final String timeSeparator = ":";
    private static final String zeroString = "0";

    private final ClockUniversalModel model;
    private final JPanel panel;
    private List<JLabel> labelList;
    private JButton deleteButton;

    private Font font = (new SupportClass()).getFont();

    public GuiClock(ClockUniversalModel model) throws HeadlessException, TripletBuildException {
        super(model.getClock().getName());
        this.model = model;
        this.panel = new JPanel(new GridLayout(0, 1));
        this.labelList = new ArrayList<>();

        JPanel buttonPanel = new JPanel(new GridLayout(0, 2));
        buttonPanel.setVisible(true);

        JButton createButton = createButtonAddAlarmClock();
        createButton.setFont(this.font);
        this.deleteButton = createButtonRemoveAlarmClock();
        this.deleteButton.setFont(this.font);
        createButton.setVisible(true);
        buttonPanel.add(createButton);
        buttonPanel.add(deleteButton);

        this.panel.add(buttonPanel);

        this.updatePanel();
        this.labelList.get(0).setForeground(Color.BLUE);

        this.add(this.panel, BorderLayout.CENTER);
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
        button.addActionListener(actionEvent -> AlarmClockGuiRemove.remove(model));
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

        label.setText(text.toString());
        label.setFont(this.font);
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
