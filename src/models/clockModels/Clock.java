package models.clockModels;

import java.time.LocalDateTime;

public class Clock {

    private String name;

    public Clock(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


    public int getSecond() {
        return LocalDateTime.now().getSecond();
    }

    public int getMinutes() {
        return LocalDateTime.now().getMinute();
    }

    public int getHours() {
        return LocalDateTime.now().getHour();
    }
}
