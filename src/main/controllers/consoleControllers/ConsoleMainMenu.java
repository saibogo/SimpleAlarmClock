package main.controllers.consoleControllers;

import main.models.clockModels.AlarmClock;
import main.models.clockModels.StopWatch;
import main.models.clockModels.Timer;
import main.support.Localisation;

import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleMainMenu {

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
        boolean notCorrectedDialog = true;
        Scanner scanner = new Scanner(System.in);
        String name = "";
        int hour = 0;
        int minute = 0;
        int seconds = 0;
        int note = 0;
        int instrument = 0;
        int volume = 0;
        while (notCorrectedDialog) {
            try {
                System.out.println(Localisation.devicesName() + ":");
                name = scanner.next();
                System.out.println(Localisation.selectTimeToAlarmClock());
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
                else notCorrectedDialog = hour < 0 || hour > 23;


            } catch (Exception e) {
                notCorrectedDialog = true;
                scanner = new Scanner(System.in);
            }
            if(notCorrectedDialog)  System.out.println(Localisation.notCorrectedData());
        }

        Date tmpDate = Calendar.getInstance().getTime();
        tmpDate.setHours(hour);
        tmpDate.setMinutes(minute);
        tmpDate.setSeconds(seconds);

        if (tmpDate.getTime() < System.currentTimeMillis()) tmpDate.setTime(tmpDate.getTime() + 24 * 3600 * 1000);
        System.out.println(Localisation.setupDeviceToTime(name, tmpDate));

        AlarmClock alarmClock = new AlarmClock.Builder()
                .setName(name)
                .setAlarmDate(tmpDate)
                .setInstrument(instrument)
                .setNote(note)
                .setVolume(volume)
                .build();
        new ConsoleAlarmClockController(alarmClock).start();
    }

    private static void createTimer() {
        boolean notCorrectedDialog = true;
        Scanner scanner = new Scanner(System.in);
        String name = "";
        int hour = 0;
        int minute = 0;
        int seconds = 0;
        int note = 0;
        int instrument = 0;
        int volume = 0;
        while (notCorrectedDialog) {
            try {
                System.out.println(Localisation.devicesName() + ":");
                name = scanner.next();
                System.out.println(Localisation.howManyToTimer());
                System.out.println(Localisation.hours() + ":");
                hour = scanner.nextInt();
                System.out.println(Localisation.minutes() + ":");
                minute = scanner.nextInt();
                System.out.println(Localisation.seconds() +  ":");
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
                else notCorrectedDialog = hour < 0;


            } catch (Exception e) {
                notCorrectedDialog = true;
                scanner = new Scanner(System.in);
            }
            if(notCorrectedDialog)  System.out.println(Localisation.notCorrectedData());
        }

        Date tmpDate = new Date(System.currentTimeMillis() + (hour * 3600 + minute * 60 + seconds) * 1000);

        System.out.println(Localisation.setupDeviceToTime(name, tmpDate));

        Timer timer = (Timer) new Timer.Builder()
                .setName(name)
                .setAlarmDate(tmpDate)
                .setNote(note)
                .setInstrument(instrument)
                .setVolume(volume)
                .build();
        new ConsoleTimerController(timer).start();
    }

    private static void createStopWatch() {
        System.out.println(Localisation.startingStopWatch());
        StopWatch stopWatch = new StopWatch();
        new ConsoleStopWatchController(stopWatch).start();
    }

}
