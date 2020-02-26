package main.views;

import main.models.clockModels.Clock;

public class ConsoleClockView {

    private final Clock clock;

    public ConsoleClockView(final Clock clock) {
        this.clock = clock;
    }

    private static String toTwoNumber(int num) {
        if (num > 9) return "" + num;
        return "0" + num;
    }

    public void show(){
        System.out.println("Time (" + this.clock.getName() + "): " + toTwoNumber(this.clock.getHours()) + ":" +
                toTwoNumber(this.clock.getMinutes()) + ":" + toTwoNumber(this.clock.getSecond()));
    }
}
