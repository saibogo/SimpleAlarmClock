package main.controllers.guiControllers;

import main.controllers.StopWatchController;

public class GuiStopWatchThread extends Thread {

    private GuiStopWatch gui;

    public GuiStopWatchThread(StopWatchController controller) {
        this.gui = new GuiStopWatch(controller);
    }

    @Override
    public void run() {
        while (gui.isRunning()) {
            this.gui.updateLabel();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
