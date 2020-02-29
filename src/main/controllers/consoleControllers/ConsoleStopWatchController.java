package main.controllers.consoleControllers;

import main.models.clockModels.StopWatch;
import main.support.Localisation;

import java.io.IOException;


public class ConsoleStopWatchController extends Thread{

    private StopWatch stopWatch;

    public ConsoleStopWatchController(StopWatch stopWatch) {
        this.stopWatch = stopWatch;
    }

    @Override
    public void run() {
        System.out.println(Localisation.pressEnter());
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.stopWatch.updateTimePassed();
        System.out.println(Localisation.timePassed() + " " + this.stopWatch.getHoursPassed() + " " +
                Localisation.hours() + " " +
                this.stopWatch.getMinutesPassed() + " " + Localisation.minutes() + " " +
                this.stopWatch.getSecondsPassed() +
                "." + this.stopWatch.getTenthPassed() + " " + Localisation.seconds());

    }
}
