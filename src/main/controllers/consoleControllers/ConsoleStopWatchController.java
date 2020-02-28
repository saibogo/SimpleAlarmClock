package main.controllers.consoleControllers;

import main.models.clockModels.StopWatch;

import java.io.IOException;


public class ConsoleStopWatchController extends Thread{

    private StopWatch stopWatch;

    public ConsoleStopWatchController(StopWatch stopWatch) {
        this.stopWatch = stopWatch;
    }

    @Override
    public void run() {
        System.out.println("Для остановки нажмите Enter");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.stopWatch.updateTimePassed();
        System.out.println("Прошло " + this.stopWatch.getHoursPassed() +
                "ч. " + this.stopWatch.getMinutesPassed() + "мин. " + this.stopWatch.getSecondsPassed() +
                "." + this.stopWatch.getTenthPassed() + "сек.");

    }
}
