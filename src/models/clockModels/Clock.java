package models.clockModels;

import java.util.Calendar;

public class Clock {

    private final String name;

    public Clock(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


    public int getSecond() {
        return Calendar.getInstance().get(Calendar.SECOND);
    }

    public int getMinutes() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

    public int getHours() {
        return Calendar.getInstance().get(Calendar.HOUR);
    }
}
