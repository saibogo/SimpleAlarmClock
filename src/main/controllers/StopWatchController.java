package main.controllers;

import main.models.clockModels.StopWatch;

public class StopWatchController extends Thread{

    private StopWatch stopWatch;

    public StopWatchController(StopWatch stopWatch) {
        this.stopWatch = stopWatch;
    }

    public StopWatch getStopWatch() {
        return stopWatch;
    }

    public void stopStopWatch() {
        this.interrupt();
    }

    @Override
    public void run() {
        while(true) {
            this.stopWatch.updateTimePassed();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
