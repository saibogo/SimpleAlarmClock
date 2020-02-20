package models.clockModels;

import controllers.AlarmClocksPullController;
import models.supportModels.Triplet;
import myException.TripletBuildException;

import java.util.Calendar;



public class ClockUniversalModel {
    private final Clock clock;
    private final AlarmClocksPullController alarmClocksPullController;

    public ClockUniversalModel(Clock clock, AlarmClocksPullController alarmClocksPullController) {
        this.clock = clock;
        this.alarmClocksPullController = alarmClocksPullController;
    }

    public int howManyElements() {
        return 1 + alarmClocksPullController.size();
    }

    public Triplet<Long> getTimeElement(int index) throws TripletBuildException {
        Triplet<Long> result;
        if (index == 0) {
            result = new  Triplet.Builder<Long>()
                    .setFirst((long) this.clock.getHours())
                    .setSecond((long) this.clock.getMinutes())
                    .setLast((long) this.clock.getSecond())
                    .build();

        } else if(index < this.howManyElements()) {
            try {
                long deltaTime = alarmClocksPullController.getControllers().get(index - 1)
                        .getAlarmClock().getAlarmDate().getTime() - Calendar.getInstance().getTimeInMillis();

                deltaTime = (deltaTime >= 0 ? deltaTime : 0) / 1000L;
                Long first = deltaTime / 3600L;
                deltaTime = deltaTime % 3600L;
                Long second = deltaTime / 60L;
                Long last = deltaTime % 60L;

                result = new Triplet.Builder<Long>()
                        .setFirst(first)
                        .setSecond(second)
                        .setLast(last)
                        .build();


            } catch (NullPointerException | TripletBuildException e) {

                result =  new Triplet.Builder<Long>()
                        .setFirst(0L)
                        .setSecond(0L)
                        .setLast(0L)
                        .build();

            }
        } else {
            throw new IndexOutOfBoundsException();
        }
        return result;
    }

    public Clock getClock() {
        return this.clock;
    }

    public AlarmClocksPullController getAlarmClocksPullController() {
        return this.alarmClocksPullController;
    }
}
