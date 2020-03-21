package main.models.supportModels;

import main.controllers.GuiStarter;
import main.controllers.TimerController;
import main.controllers.guiControllers.GuiTimer;
import main.controllers.guiControllers.GuiTimerThread;
import main.controllers.guiDialogs.MainSelectedMenu;
import main.models.clockModels.AlarmClock;
import main.models.clockModels.ClockUniversalModel;
import main.models.clockModels.Timer;
import main.support.DevicesType;
import main.support.Localisation;
import main.support.SupportClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class GuiCreatorModel{

    private static long msInSecond = 1000L;
    private static long secInMinutes = 60L;
    private static long secInHour = 60 * secInMinutes;

    private DevicesType devicesType;
    private ClockUniversalModel clockUniversalModel;
    private SpinnerDateModel model = new SpinnerDateModel();
    private JTextField fieldName = new JTextField(15);
    private SpinnerNumberModel hoursModel = new SpinnerNumberModel();
    private SpinnerNumberModel minutesModel = new SpinnerNumberModel();
    private SpinnerNumberModel secondsModel = new SpinnerNumberModel();

    public GuiCreatorModel(DevicesType devicesType) throws HeadlessException {
        this.devicesType = devicesType;
        this.create();
    }

    public GuiCreatorModel(DevicesType devicesType, ClockUniversalModel clockUniversalModel) {
        this.devicesType = devicesType;
        this.clockUniversalModel = clockUniversalModel;
        this.create();
    }

    private void create() {
        JFrame frame = new JFrame(devicesType == DevicesType.ALARMCLOCK ?
                Localisation.addAlarmClock() : Localisation.timerParameters());
        frame.setLayout(new GridLayout(0, 1));
        frame.setIconImage(SupportClass.getImageIcon());

        if (devicesType == DevicesType.TIMER) {
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    frame.dispose();
                    GuiStarter.startGui();
                }
            });
        } else if (devicesType == DevicesType.ALARMCLOCK) {
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }


        JPanel nameAndDataPanel = new JPanel(new GridLayout(0 , 2));
        if (devicesType == DevicesType.ALARMCLOCK) {
            nameAndDataPanel.add(new JLabel(Localisation.devicesName()));
            fieldName.setText(Localisation.alarmClock());
            nameAndDataPanel.add(fieldName);

            nameAndDataPanel.add(new JLabel(Localisation.setupTime()));
            long addMsToAlarm = 300000L;
            model.setValue(new Date(System.currentTimeMillis() + addMsToAlarm));
            JSpinner spinner = new JSpinner(model);
            spinner.getEditor().getComponent(0).setBackground(SupportClass.panelColor);
            nameAndDataPanel.add(spinner);

        } else if (devicesType == DevicesType.TIMER) {
            JLabel labelName =  new JLabel(Localisation.devicesName());
            labelName.setVisible(true);
            fieldName.setText(Localisation.timer());
            fieldName.setVisible(true);
            nameAndDataPanel.add(labelName);
            nameAndDataPanel.add(fieldName);

            JLabel hours = new JLabel(Localisation.hours());
            hours.setVisible(true);
            JSpinner hoursSpinner = new JSpinner();
            hoursModel.setMinimum(0);
            hoursModel.setMaximum(99);
            hoursSpinner.setModel(hoursModel);
            hoursSpinner.getEditor().getComponent(0).setBackground(SupportClass.panelColor);
            hoursSpinner.setVisible(true);
            nameAndDataPanel.add(hours);
            nameAndDataPanel.add(hoursSpinner);

            JLabel minutes = new JLabel(Localisation.minutes());
            minutes.setVisible(true);
            JSpinner minutesSpinner = new JSpinner();
            minutesModel.setMinimum(0);
            minutesModel.setMaximum(1000);
            minutesSpinner.setModel(minutesModel);
            minutesSpinner.getEditor().getComponent(0).setBackground(SupportClass.panelColor);
            minutesSpinner.setVisible(true);
            nameAndDataPanel.add(minutes);
            nameAndDataPanel.add(minutesSpinner);

            JLabel seconds = new JLabel(Localisation.seconds());
            seconds.setVisible(true);
            JSpinner secondsSpinner = new JSpinner();
            secondsModel.setMinimum(0);
            secondsModel.setMaximum(1000);
            secondsSpinner.setModel(secondsModel);
            secondsSpinner.getEditor().getComponent(0).setBackground(SupportClass.panelColor);
            secondsSpinner.setVisible(true);
            nameAndDataPanel.add(seconds);
            nameAndDataPanel.add(secondsSpinner);
        }


        nameAndDataPanel.setVisible(true);
        frame.add(nameAndDataPanel);

        SelectVolumeNoteInstrument selectPanel = new SelectVolumeNoteInstrument();
        selectPanel.setVisible(true);
        frame.add(selectPanel.toJPanel());

        ButtonCreateCancelPanel buttonPanel = new ButtonCreateCancelPanel(this.devicesType);
        buttonPanel.setVisible(true);

        if (this.devicesType == DevicesType.ALARMCLOCK) {
            buttonPanel.addListenerToCancelButton(actionEvent -> frame.dispose());
            buttonPanel.addListenerToCreateButton(actionEvent -> {
                if (model.getDate().getTime() > System.currentTimeMillis()) {

                    AlarmClock ac = new AlarmClock.Builder()
                            .setName(fieldName.getText())
                            .setAlarmDate(model.getDate())
                            .setVolume(selectPanel.getVolume())
                            .setNote(selectPanel.getNote())
                            .setInstrument(selectPanel.getInstrument())
                            .build();
                    clockUniversalModel.getAlarmClocksPullController().addAlarmClock(ac);
                    frame.dispose();
                } else {
                    JOptionPane.showConfirmDialog(new Frame(),
                            Localisation.timeTooSmall(),
                            Localisation.notCorrectedTime(), JOptionPane.DEFAULT_OPTION);
                }
            });
        } else if (this.devicesType == DevicesType.TIMER) {
            buttonPanel.addListenerToCancelButton(actionEvent -> System.exit(0));
            buttonPanel.addListenerToCreateButton(actionEvent -> {
                long deltaTime = (int)hoursModel.getNumber() * secInHour +
                        (int)minutesModel.getNumber() * secInMinutes +
                        (int)secondsModel.getNumber();
                Timer timer = (Timer) new Timer.Builder()
                        .setName(fieldName.getText())
                        .setAlarmDate(new Date(System.currentTimeMillis() + deltaTime * msInSecond))
                        .setVolume(selectPanel.getVolume())
                        .setNote(selectPanel.getNote())
                        .setInstrument(selectPanel.getInstrument())
                        .build();
                TimerController controller = new TimerController(timer);
                GuiTimer guiTimer = new GuiTimer(controller);
                controller.start();
                GuiTimerThread guiTimerThread = new GuiTimerThread(guiTimer);
                guiTimerThread.start();
                frame.dispose();
            });
        }

        buttonPanel.setVisible(true);
        frame.add(buttonPanel.toJPanel());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
