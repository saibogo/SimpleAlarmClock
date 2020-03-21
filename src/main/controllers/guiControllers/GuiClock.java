package main.controllers.guiControllers;

import main.controllers.AlarmClockController;
import main.controllers.GuiStarter;
import main.models.clockModels.ClockUniversalModel;
import main.controllers.guiDialogs.SelectToRemoveAlarmClockGui;
import main.models.supportModels.GuiCreatorModel;
import main.models.supportModels.Triplet;
import main.myException.TripletBuildException;
import main.support.DevicesType;
import main.support.Localisation;
import main.support.SupportClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class GuiClock extends JFrame {

    private static int columns = 5;

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
        this.setIconImage(SupportClass.getImageIcon());

        JPanel buttonPanel = new JPanel(new GridLayout(0, columns));
        buttonPanel.setVisible(true);

        JButton createButton = createButtonAddAlarmClock();
        this.deleteButton = createButtonRemoveAlarmClock();

        createButton.setVisible(true);

        buttonPanel.add(new JLabel());
        buttonPanel.add(createButton);
        buttonPanel.add(new JLabel());
        buttonPanel.add(deleteButton);
        buttonPanel.add(new JLabel());

        this.panel.add(buttonPanel);

        this.updatePanel();
        this.labelList.get(0).setForeground(SupportClass.clockColor);
        this.labelList.get(0).setHorizontalAlignment(SwingConstants.CENTER);
        this.labelList.get(0).setToolTipText(model.getClock().getName());

        this.add(this.panel, BorderLayout.CENTER);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                while(model.getAlarmClocksPullController().size() > 0) {
                    model.getAlarmClocksPullController().getControllers().get(0).stopAlarmClock();
                    model.removeAlarmClockController(0);
                }
                GuiStarter.startGui();
            }
        });
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    private JButton createButtonAddAlarmClock() {
        JButton button = new JButton("+");
        button.addActionListener(actionEvent -> new GuiCreatorModel(DevicesType.ALARMCLOCK, model));
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
                int lastLabelIndex = this.labelList.size() - 1;
                this.labelList.get(lastLabelIndex).setHorizontalAlignment(SwingConstants.CENTER);
                this.labelList.get(lastLabelIndex).setForeground(SupportClass.alarmClockColor);
                if (clockCount != 1) {
                    this.labelList.get(lastLabelIndex).setToolTipText(this.model.getAlarmClocksPullController()
                            .getAlarmClocks().getLast().getName());
                }
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
