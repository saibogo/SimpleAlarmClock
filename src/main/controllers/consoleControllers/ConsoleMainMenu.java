package main.controllers.consoleControllers;

import main.models.clockModels.AlarmClock;
import main.models.clockModels.StopWatch;
import main.models.clockModels.Timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class ConsoleMainMenu {

    public static void selectClockType() {
        boolean notCorrectedDialog = true;
        Scanner scanner = new Scanner(System.in);
        int typeDevices = 0;
        while (notCorrectedDialog) {
            System.out.println("Выберите тип устройства:");
            System.out.println("1 - Будильник\n2 - Таймер\n3 - Секундомер\n0 - Выход");
            typeDevices = scanner.nextInt();
            if (typeDevices >= 0 && typeDevices < 4) notCorrectedDialog = false;
            else System.out.println("Некорректный ввод. Попробуйте еще раз!");
        }
        switch (typeDevices) {
            case 1 : System.out.println("Выбран будильник");
                    createAlarmClock();
                    break;
            case 2 : System.out.println("Выбран таймер");
                    createTimer();
                    break;
            case 3 : System.out.println("Секундомер");
                    createStopWatch();
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
                System.out.println("Название будильника :");
                name = scanner.next();
                System.out.println("Час срабатывания:");
                hour = scanner.nextInt();
                System.out.println("Минуты:");
                minute = scanner.nextInt();
                System.out.println("Секунды:");
                seconds = scanner.nextInt();
                System.out.println("Нота будильника(от 0 до 131 включительно)");
                note = scanner.nextInt();
                System.out.println("Номер музыкального инструмента (от 1 до 128 включительно)");
                instrument = scanner.nextInt();
                System.out.println("Громкость звука в процентах % (от 0 до 100)");
                volume = scanner.nextInt();

                if (volume < 0 || volume > 100) notCorrectedDialog = true;
                else if (instrument < 1 || instrument > 128) notCorrectedDialog = true;
                else if (note < 0 || note > 131) notCorrectedDialog = true;
                else if (seconds < 0 || seconds > 59) notCorrectedDialog = true;
                else if (minute < 0 || minute > 59) notCorrectedDialog = true;
                else notCorrectedDialog = hour < 0 || hour > 23;


            } catch (Exception e) {
                notCorrectedDialog = true;
            }
            if(notCorrectedDialog)  System.out.println("Некорректные данные. Попробуйте снова!");
        }

        Date tmpDate = Calendar.getInstance().getTime();
        tmpDate.setHours(hour);
        tmpDate.setMinutes(minute);
        tmpDate.setSeconds(seconds);

        if (tmpDate.getTime() < System.currentTimeMillis()) tmpDate.setTime(tmpDate.getTime() + 24 * 3600 * 1000);
        System.out.println("Устанавливается будильник " + name  + " на " + tmpDate);

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
                System.out.println("Название таймера:");
                name = scanner.next();
                System.out.println("Сколько часов:");
                hour = scanner.nextInt();
                System.out.println("Сколько минут:");
                minute = scanner.nextInt();
                System.out.println("Сколько секунд:");
                seconds = scanner.nextInt();
                System.out.println("Нота оповещения(от 0 до 131 включительно)");
                note = scanner.nextInt();
                System.out.println("Номер музыкального инструмента (от 1 до 128 включительно)");
                instrument = scanner.nextInt();
                System.out.println("Громкость звука в процентах % (от 0 до 100)");
                volume = scanner.nextInt();

                if (volume < 0 || volume > 100) notCorrectedDialog = true;
                else if (instrument < 1 || instrument > 128) notCorrectedDialog = true;
                else if (note < 0 || note > 131) notCorrectedDialog = true;
                else if (seconds < 0 || seconds > 59) notCorrectedDialog = true;
                else if (minute < 0 || minute > 59) notCorrectedDialog = true;
                else notCorrectedDialog = hour < 0;


            } catch (Exception e) {
                notCorrectedDialog = true;
            }
            if(notCorrectedDialog)  System.out.println("Некорректные данные. Попробуйте снова!");
        }

        Date tmpDate = new Date(System.currentTimeMillis() + (hour * 3600 + minute * 60 + seconds) * 1000);

        System.out.println("Устанавливается таймер " + name  + " на " + tmpDate);

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
        System.out.println("Запущен консольный секундомер");
        StopWatch stopWatch = new StopWatch();
        new ConsoleStopWatchController(stopWatch).start();
    }

}
