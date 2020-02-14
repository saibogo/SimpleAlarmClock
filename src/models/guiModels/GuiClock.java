package models.guiModels;

import models.clockModels.ClockUniversalModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.StringJoiner;

public class GuiClock extends JFrame {

    private ClockUniversalModel model;
    private JPanel panel;

    public GuiClock(ClockUniversalModel model) throws HeadlessException {
        super(model.getClock().getName());
        this.model = model;
        this.panel = new JPanel(new FlowLayout());
        this.updatePanel();
        this.add(this.panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    private JLabel createNewLabel(List<Long> timeList) {
        JLabel label = new JLabel();
        StringJoiner text = new StringJoiner(":");
        for (Long item: timeList) {
            if (item > 9) text.add("" + item);
            else text.add("0" + item);
        }
        label.setText(" " + text.toString() + " ");
        label.setFont(SupportClass.FONT1);
        return label;
    }

    protected void updatePanel(){
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
