import controllers.AlarmClocksPullController;
import models.MemoryUsed;
import models.clockModels.AlarmClocksPull;
import models.clockModels.Clock;
import models.clockModels.ClockUniversalModel;
import models.guiModels.GuiClock;
import models.guiModels.GuiThread;
import myException.TripletBuildException;


public class Main {

    public static void main(String[] args) throws TripletBuildException {

        Clock clock = new Clock("First clock");

        AlarmClocksPullController controller = new AlarmClocksPullController(new AlarmClocksPull());

        controller.start();

        ClockUniversalModel model = new ClockUniversalModel(clock, controller);

        GuiClock gui = new GuiClock(model);
        GuiThread thread = new GuiThread(gui);
        thread.start();

        MemoryUsed used = new MemoryUsed();

        for (int i = 0; i < 1000; i++) {
            used.show();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
