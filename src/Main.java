import controllers.AlarmClocksPullController;
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

    }
}
