package models.clockModels;

import controllers.AlarmClocksPullController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClockUniversalModel {
    private Clock clock;
    private AlarmClocksPullController controller;

    public ClockUniversalModel(Clock clock, AlarmClocksPullController controller) {
        this.clock = clock;
        this.controller = controller;
    }

    public int howManyElements() {
        return 1 + controller.size();
    }

    public List<Long> getTimeElement(int index) {
        List<Long> result = new ArrayList<>();
        if (index == 0) {
            result.add((long) this.clock.getHours());
            result.add((long) this.clock.getMinutes());
            result.add((long) this.clock.getSecond());
        } else if(index < this.howManyElements()) {
            try {
                Long deltaTime = controller.getControllers().get(index - 1).getAlarmClock().getAlarmDate().getTime() -
                        (new Date()).getTime();
                deltaTime = deltaTime >= 0 ? deltaTime : 0;
                deltaTime = deltaTime / 1000;
                result.add(deltaTime / 3600);
                deltaTime = deltaTime % 3600;
                result.add(deltaTime / 60);
                result.add(deltaTime % 60);
            } catch (NullPointerException e) {
                result.clear();
                result.add(0L);
                result.add(0L);
                result.add(0L);
                return result;
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
        return result;
    }

    public Clock getClock() {
        return this.clock;
    }
}
