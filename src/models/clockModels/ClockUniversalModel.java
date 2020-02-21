package models.clockModels;

import controllers.AlarmClocksPullController;
import models.supportModels.Triplet;
import myException.TripletBuildException;



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
            result = this.clock.getTimeTriplet();

        } else if(index < this.howManyElements()) {
            try {
                result = this.alarmClocksPullController.getControllers().
                        get(index - 1).getAlarmClock().getTimeTriplet();


            } catch (NullPointerException e) {

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
