package models.clockModels;

import myException.AlarmExistsException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlarmClocksPull {

    private final List<AlarmClock> alarmClockList;

    public AlarmClocksPull() {
        this.alarmClockList = new ArrayList<>();
    }

    public void addAlarmClock(final AlarmClock alarmClock) throws AlarmExistsException {

        Date newAlarmDate = alarmClock.getAlarmDate();
        for (AlarmClock clock: alarmClockList) {
            if (clock.getAlarmDate().equals(newAlarmDate)) {
                throw new AlarmExistsException();
            }
        }
        this.alarmClockList.add(alarmClock);
    }

    public void removeAlarmClock(final AlarmClock alarmClock) {
        this.alarmClockList.remove(alarmClock);
    }

    public List<AlarmClock> getAlarmClockList() {
        return new ArrayList<>(this.alarmClockList);
    }

    public int size() {
        return this.alarmClockList.size();
    }
}
