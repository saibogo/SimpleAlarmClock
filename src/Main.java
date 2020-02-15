import controllers.AlarmClockController;
import controllers.AlarmClocksPullController;
import models.*;
import models.clockModels.AlarmClock;
import models.clockModels.AlarmClocksPull;
import models.clockModels.Clock;
import models.clockModels.ClockUniversalModel;
import models.guiModels.GuiClock;
import models.guiModels.GuiThread;


import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException, TripletBuildException {

        Clock clock = new Clock("First clock");

        AlarmClock alarmClock1 = new AlarmClock.Builder()
                .setName("First alarm clock")
                .setAlarmDate(new Date((new Date()).getTime() + 20000))
                .setNote(43)
                .setInstrument(27)
                .build();

        AlarmClock alarmClock2 = new AlarmClock.Builder()
                .setName("Second alarm clock")
                .setAlarmDate(new Date((new Date()).getTime() + 40000))
                .setNote(43)
                .setInstrument(46)
                .build();


        MemoryUsed used = new MemoryUsed();

        AlarmClocksPullController controller = new AlarmClocksPullController(new AlarmClocksPull());

        controller.addAlarmClock(alarmClock1);
        controller.addAlarmClock(alarmClock2);
        controller.start();

        ClockUniversalModel model = new ClockUniversalModel(clock, controller);

        GuiClock gui = new GuiClock(model);
        GuiThread thread = new GuiThread(gui);
        thread.start();

        boolean flag = true;

        while (flag) {
            Thread.sleep(1000);
            if (controller.size() == 0) flag = false;
            else {
                List<AlarmClockController> tmp = controller.getControllers();
                for (AlarmClockController item: tmp) {
                    if (item.getAlarmClock().alarmIsRun()) {
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Отложить будильник " + item.getAlarmClock().getName() + " ?(1 - да)");
                        String command = scanner.nextLine();
                        if (command.equals("1")) item.getAlarmClock().appendTime(30000L);
                        else item.stopAlarmClock();
                        break;
                    }
                }
            }
        }

    }
}
