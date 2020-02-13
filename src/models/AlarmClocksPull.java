package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlarmClocksPull {

    private List<AlarmClock> alarmClockList;

    public AlarmClocksPull() {
        this.alarmClockList = new ArrayList<>();
    }

    public boolean addAlarmClock(final AlarmClock alarmClock) throws AlarmExistsException {

        Date newAlarmDate = alarmClock.getAlarmDate();
        for (AlarmClock clock: alarmClockList) {
            if (clock.getAlarmDate().equals(newAlarmDate)) {
                throw new AlarmExistsException();
            }
        }
        this.alarmClockList.add(alarmClock);
        return true;
    }

    public boolean removeAlarmClock(final AlarmClock alarmClock) {
        if (this.alarmClockList.contains(alarmClock)) {
            this.alarmClockList.remove(alarmClock);
            return true;
        }
        return false;
    }

    public List<AlarmClock> getAlarmClockList() {
        return new ArrayList<>(this.alarmClockList);
    }

    public int size() {
        return this.alarmClockList.size();
    }
}
