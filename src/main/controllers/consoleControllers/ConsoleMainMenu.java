package main.controllers.consoleControllers;

import main.models.clockModels.AlarmClock;
import main.models.clockModels.StopWatch;
import main.models.clockModels.Timer;
import main.support.Localisation;

import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleMainMenu {

    private static long msInSec = 1000;
    private static long secInMinute = 60;
    private static long secInHour = 3600;
    private static long hourInDay = 24;

    public static void selectClockType() {
        boolean notCorrectedDialog = true;
        Scanner scanner = new Scanner(System.in);
        int typeDevices = 0;
        while (notCorrectedDialog) {
            System.out.println(Localisation.selectDevice());
            System.out.println("1 - " + Localisation.alarmClock() + "\n2 - " + Localisation.timer() +
                    "\n3 - " + Localisation.stopWatch() + "\n4 - " + Localisation.changeLanguage() + "\n0 - " +
                    Localisation.exitString());
            try {
                typeDevices = scanner.nextInt();
                if (typeDevices >= 0 && typeDevices < 5) notCorrectedDialog = false;
            } catch (InputMismatchException e) {
                typeDevices = 0;
                scanner = new Scanner(System.in);
            }

            if (notCorrectedDialog)System.out.println(Localisation.notCorrectedData());
        }
        switch (typeDevices) {
            case 1 : System.out.println(Localisation.alarmClock());
                    createAlarmClock();
                    break;
            case 2 : System.out.println(Localisation.timer());
                    createTimer();
                    break;
            case 3 : System.out.println(Localisation.stopWatch());
                    createStopWatch();
                    break;
            case 4 : if (Localisation.getCurrentLanguage() == Localisation.Languages.RU) {
                Localisation.setCurrentLang(Localisation.Languages.ENG);
            } else {
                Localisation.setCurrentLang(Localisation.Languages.RU);
            }
            selectClockType();
                    break;
            default: System.exit(0);
        }
    }

    public static void createAlarmClock() {
        Builder builder = new Builder(DeviceType.ALARMCLOCK);
        builder.inputDataToCreateDevice();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, builder.getHour());
        calendar.set(Calendar.MINUTE, builder.getMinute());
        calendar.set(Calendar.SECOND, builder.getSeconds());

        if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
            calendar.setTimeInMillis(calendar.getTimeInMillis() + hourInDay * secInHour * msInSec);
        }
        System.out.println(Localisation.setupDeviceToTime(builder.getName(), calendar.getTime()));

        AlarmClock alarmClock = new AlarmClock.Builder()
                .setName(builder.getName())
                .setAlarmDate(calendar.getTime())
                .setInstrument(builder.getInstrument())
                .setNote(builder.getNote())
                .setVolume(builder.getVolume())
                .build();
        new ConsoleAlarmClockController(alarmClock).start();
    }

    private static void createTimer() {
        Builder builder = new Builder(DeviceType.TIMER);
        builder.inputDataToCreateDevice();

        Calendar calendar = Calendar.getInstance();
        int msInDay = (int) ((builder.getHour() * secInHour + builder.getMinute() * secInMinute +
                builder.getSeconds()) * msInSec);
        calendar.setTimeInMillis(System.currentTimeMillis() + msInDay);

        System.out.println(Localisation.setupDeviceToTime(builder.getName(), calendar.getTime()));

        Timer timer = (Timer) new Timer.Builder()
                .setName(builder.getName())
                .setAlarmDate(calendar.getTime())
                .setNote(builder.getNote())
                .setInstrument(builder.getInstrument())
                .setVolume(builder.getVolume())
                .build();
        new ConsoleTimerController(timer).start();
    }

    private static void createStopWatch() {
        System.out.println(Localisation.startingStopWatch());
        StopWatch stopWatch = new StopWatch();
        new ConsoleStopWatchController(stopWatch).start();
    }

    private static enum DeviceType {
        TIMER, ALARMCLOCK
    }

    public static class Builder {
        private DeviceType deviceType;
        private String name = "";
        private int hour = 0;
        private int minute = 0;
        private int seconds = 0;
        private int note = 0;
        private int instrument = 0;
        private int volume = 0;

        public Builder(DeviceType deviceType) {
            this.deviceType = deviceType;
        }

        public void inputDataToCreateDevice() {
            boolean notCorrectedDialog = true;
            Scanner scanner = new Scanner(System.in);

            while (notCorrectedDialog) {
                try {
                    System.out.println(Localisation.devicesName() + ":");
                    name = scanner.nextLine();
                    if (deviceType == DeviceType.ALARMCLOCK) {
                        System.out.println(Localisation.selectTimeToAlarmClock());
                    } else {
                        System.out.println(Localisation.howManyToTimer());
                    }
                    System.out.println(Localisation.hours() + ":");
                    hour = scanner.nextInt();
                    System.out.println(Localisation.minutes() + ":");
                    minute = scanner.nextInt();
                    System.out.println(Localisation.seconds() + ":");
                    seconds = scanner.nextInt();
                    System.out.println(Localisation.noteInstrument());
                    note = scanner.nextInt();
                    System.out.println(Localisation.instrumentNumber());
                    instrument = scanner.nextInt();
                    System.out.println(Localisation.volume());
                    volume = scanner.nextInt();

                    if (volume < 0 || volume > 100) notCorrectedDialog = true;
                    else if (instrument < 1 || instrument > 128) notCorrectedDialog = true;
                    else if (note < 0 || note > 131) notCorrectedDialog = true;
                    else if (seconds < 0 || seconds > 59) notCorrectedDialog = true;
                    else if (minute < 0 || minute > 59) notCorrectedDialog = true;
                    else notCorrectedDialog = (hour < 0 || (hour > 23 && deviceType == DeviceType.ALARMCLOCK));


                } catch (Exception e) {
                    notCorrectedDialog = true;
                    scanner = new Scanner(System.in);
                }
                if(notCorrectedDialog)  System.out.println(Localisation.notCorrectedData());
            }

        }

        public String getName() {
            return name;
        }

        public int getHour() {
            return hour;
        }

        public int getMinute() {
            return minute;
        }

        public int getSeconds() {
            return seconds;
        }

        public int getNote() {
            return note;
        }

        public int getInstrument() {
            return instrument;
        }

        public int getVolume() {
            return volume;
        }
    }
}
