package models.guiModels;

import models.TripletBuildException;

public class GuiThread extends Thread {

    private GuiClock guiClock;

    public GuiThread(GuiClock guiClock) {
        this.guiClock = guiClock;
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.guiClock.updatePanel();
            } catch (TripletBuildException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
