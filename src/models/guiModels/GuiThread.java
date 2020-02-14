package models.guiModels;

public class GuiThread extends Thread {

    private GuiClock guiClock;

    public GuiThread(GuiClock guiClock) {
        this.guiClock = guiClock;
    }

    @Override
    public void run() {
        while (true) {
            this.guiClock.updatePanel();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
