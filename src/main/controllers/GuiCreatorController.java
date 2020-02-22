package main.controllers;

import main.clockModels.AlarmClocksPull;
import main.clockModels.Clock;
import main.clockModels.ClockUniversalModel;
import main.models.guiModels.GuiClock;
import main.models.guiModels.GuiThread;
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
        GuiThread thread = new GuiThread(gui);
        thread.start();

    }
}
