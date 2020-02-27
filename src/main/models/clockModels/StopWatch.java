package main.models.clockModels;

import java.util.Calendar;
import java.util.Date;

public class StopWatch {

    private Date startDate;
    private long timePassed;
    private static long msInTenth = 100;
    private static long msInSeconds = 10 * msInTenth;
    private static long msInMinute = 60 * msInSeconds;
    private static long msInHours = 60 * msInMinute;


    public StopWatch() {
        this.startDate = Calendar.getInstance().getTime();
    }

    public long getTimePassed() {
        return Calendar.getInstance().getTimeInMillis() - startDate.getTime();
    }

    public void updateTimePassed() {
        this.timePassed = this.getTimePassed();
    }

    public long getHoursPassed() {
        return this.timePassed / (msInHours);
    }

    public long getMinutesPassed() {
        return (this.timePassed % (msInHours)) / msInMinute;
    }

    public long getSecondsPassed() {
        return ((this.timePassed % (msInHours)) % msInMinute) / msInSeconds;
    }

    public long getTenthPassed() {
        return (((this.timePassed % (msInHours)) % msInMinute) % msInSeconds) / msInTenth;
    }


}
