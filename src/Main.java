import controllers.AlarmClocksPullController;
import models.*;
import models.clockModels.AlarmClock;
import models.clockModels.AlarmClocksPull;
import models.clockModels.Clock;
import models.clockModels.ClockUniversalModel;
import models.guiModels.GuiClock;
import models.guiModels.GuiThread;
import views.ConsoleUniversalModelView;

import java.util.Date;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Clock clock = new Clock("First clock");
        AlarmClock alarmClock1 = new AlarmClock("First alarm clock", new Date((new Date()).getTime() + 20000));
        AlarmClock alarmClock2 = new AlarmClock("Second alarm clock", new Date((new Date()).getTime() + 40000));


        AlarmClocksPullController controller = new AlarmClocksPullController(new AlarmClocksPull());

        controller.addAlarmClock(alarmClock1);
        controller.addAlarmClock(alarmClock2);
        controller.start();

        ClockUniversalModel model = new ClockUniversalModel(clock, controller);
        ConsoleUniversalModelView view = new ConsoleUniversalModelView(model);

        GuiClock gui = new GuiClock(model);
        GuiThread thread = new GuiThread(gui);
        thread.start();

    }
}
