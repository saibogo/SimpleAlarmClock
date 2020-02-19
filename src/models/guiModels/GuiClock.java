package models.guiModels;

import controllers.AlarmClockController;
import controllers.AlarmClockGuiCreator;
import myException.TripletBuildException;
import models.clockModels.ClockUniversalModel;
import models.supportModels.Triplet;

import javax.swing.*;
import java.awt.*;
import java.util.StringJoiner;
import java.util.List;

public class GuiClock extends JFrame {

    private ClockUniversalModel model;
    private JPanel panel;

    public GuiClock(ClockUniversalModel model) throws HeadlessException, TripletBuildException {
        super(model.getClock().getName());
        this.model = model;
        this.panel = new JPanel(new GridLayout(0, 1));
        this.updatePanel();
        this.add(this.panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    private JButton createButtonAddAlarmClock() {
        JButton button = new JButton("+");
        button.setFont(SupportClass.FONT1);
        button.setSize(SupportClass.FONT1.getSize(), SupportClass.FONT1.getSize());
        button.addActionListener(actionEvent -> AlarmClockGuiCreator.create(model));
        return button;
    }

    private JButton createButtonRemoveAlarmClock() {
        JButton button = new JButton("-");
        button.setFont(SupportClass.FONT1);
        button.setSize(SupportClass.FONT1.getSize(), SupportClass.FONT1.getSize());
        button.addActionListener(actionEvent -> System.out.println("Нажали -"));
        return button;
    }


    private JLabel createNewLabel(Triplet<Long> timeTriplet) {
        JLabel label = new JLabel();
        StringJoiner text = new StringJoiner(":");

        if (timeTriplet.getFirst() > 9) text.add("" + timeTriplet.getFirst());
        else text.add("0" + timeTriplet.getFirst());

        if (timeTriplet.getSecond() > 9) text.add("" + timeTriplet.getSecond());
        else text.add("0" + timeTriplet.getSecond());

        if (timeTriplet.getLast() > 9) text.add("" + timeTriplet.getLast());
        else text.add("0" + timeTriplet.getLast());

        label.setText(" ".repeat(6) + text.toString() + " ".repeat(6));
        label.setFont(SupportClass.FONT1);
        return label;
    }

    protected void updatePanel() throws TripletBuildException {
        panel.removeAll();
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(createButtonAddAlarmClock());
        int clockCount = model.howManyElements();
        if (clockCount > 1) buttonPanel.add(createButtonRemoveAlarmClock());
        panel.add(buttonPanel);
        for (int i = 0; i < clockCount; i++){
            panel.add(createNewLabel(this.model.getTimeElement(i)), BorderLayout.NORTH);
        }
        this.setSize(SupportClass.calculateWidth(SupportClass.FONT1),
                SupportClass.calculateHeight(this.model, SupportClass.FONT1));
        panel.updateUI();

    }

    public List<AlarmClockController> getControllersList() {
        return this.model.getAlarmClocksPullController().getControllers();
    }

}
