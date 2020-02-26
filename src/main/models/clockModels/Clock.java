package main.models.clockModels;

import main.models.supportModels.Triplet;
import main.myException.TripletBuildException;

import java.util.Calendar;

public class Clock {

    private final String name;
    private Triplet<Long> timeTriplet;

    {
        try {
            timeTriplet = new Triplet.Builder<Long>().setFirst(0L).setSecond(0L).setLast(0L).build();
        } catch (TripletBuildException e) {
            e.printStackTrace();
        }
    }

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
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    public Triplet<Long> getTimeTriplet() {
        this.timeTriplet.setFirst((long) this.getHours());
        this.timeTriplet.setSecond((long) this.getMinutes());
        this.timeTriplet.setLast((long) this.getSecond());
        return this.timeTriplet;
    }

}
