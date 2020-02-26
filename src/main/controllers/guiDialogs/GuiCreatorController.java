package main.controllers.guiDialogs;

import main.controllers.AlarmClocksPullController;
import main.models.clockModels.AlarmClocksPull;
import main.models.clockModels.Clock;
import main.models.clockModels.ClockUniversalModel;
import main.controllers.guiControllers.GuiClock;
import main.controllers.guiControllers.GuiAlarmClockThread;
import main.myException.TripletBuildException;

public class GuiCreatorController {


    public GuiCreatorController(String name) {

        Clock clock = new Clock(name);

        AlarmClocksPullController controller = new AlarmClocksPullController(new AlarmClocksPull());

        controller.start();

        ClockUniversalModel model = new ClockUniversalModel(clock, controller);

        GuiClock gui = null;
        try {
            gui = new GuiClock(model);
        } catch (TripletBuildException e) {
            e.printStackTrace();
        }
        GuiAlarmClockThread thread = new GuiAlarmClockThread(gui);
        thread.start();

    }
}
