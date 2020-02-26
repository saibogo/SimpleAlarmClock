package main.models.clockModels;

public class Timer extends AlarmClock {

    private Timer(Builder builder){
        super(builder);
    }

    public static class Builder extends AlarmClock.Builder {

        @Override
        public Timer build() {
            return new Timer(this);
        }
    }
}
