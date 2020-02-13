import controllers.AlarmClocksPullController;
import models.*;
import views.ConsoleUniversalModelView;

import java.util.Date;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Clock clock = new Clock("First clock");
        MemoryUsed usedBytes = new MemoryUsed();
        AlarmClock alarmClock1 = new AlarmClock("First alarm clock", new Date((new Date()).getTime() + 20000));
        AlarmClock alarmClock2 = new AlarmClock("Second alarm clock", new Date((new Date()).getTime() + 40000));


        AlarmClocksPullController controller = new AlarmClocksPullController(new AlarmClocksPull());

        controller.addAlarmClock(alarmClock1);
        controller.addAlarmClock(alarmClock2);
        controller.start();

        ClockUniversalModel model = new ClockUniversalModel(clock, controller);
        ConsoleUniversalModelView view = new ConsoleUniversalModelView(model);

        for (int i = 0; i < 100; i++) {
            view.show();
            usedBytes.show();
            if (controller.size() == 0) {
                controller.interrupt();
                break;
            }
            Thread.sleep(1000);
        }

    }
}
