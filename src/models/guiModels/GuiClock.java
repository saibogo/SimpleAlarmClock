package models.guiModels;

import models.TripletBuildException;
import models.clockModels.ClockUniversalModel;
import models.supportModels.Triplet;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.StringJoiner;

public class GuiClock extends JFrame {

    private ClockUniversalModel model;
    private JPanel panel;

    public GuiClock(ClockUniversalModel model) throws HeadlessException, TripletBuildException {
        super(model.getClock().getName());
        this.model = model;
        this.panel = new JPanel(new FlowLayout());
        this.updatePanel();
        this.add(this.panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

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

        label.setText(" " + text.toString() + " ");
        label.setFont(SupportClass.FONT1);
        return label;
    }

    protected void updatePanel() throws TripletBuildException {
        panel.removeAll();
        int clockCount = model.howManyElements();
        JLabel label;
        for (int i = 0; i < clockCount; i++){
            panel.add(createNewLabel(this.model.getTimeElement(i)), BorderLayout.NORTH);
        }
        this.setSize(SupportClass.calculateWidth(this.model, SupportClass.FONT1),
                SupportClass.calculateHeight(this.model, SupportClass.FONT1));
        panel.updateUI();

    }
}
