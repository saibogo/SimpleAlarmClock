package main.controllers;

import main.clockModels.ClockUniversalModel;

import java.util.List;
import java.util.Scanner;

public class AlarmClockGuiRemove {

    public static void remove(ClockUniversalModel clockUniversalModel){
        List<AlarmClockController> controllers = clockUniversalModel.getAlarmClocksPullController().getControllers();
        for (int i = 0; i < controllers.size(); i++) {
            System.out.println(i + " -> " + controllers.get(i).getAlarmClock().getName() +
                    " -> " + controllers.get(i).getAlarmClock().getAlarmDate().toString());
        }

        System.out.println("\nSelect AlarmClock to Remove(input number)");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        if (num >= 0 && num < controllers.size()) {
            clockUniversalModel.removeAlarmClockController(num);
            clockUniversalModel.getAlarmClocksPullController().getControllers().remove(num);
        } else {
            System.out.println("Error number!");
        }

    }
}
