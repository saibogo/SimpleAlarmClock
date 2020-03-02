package main.support;

import java.util.Date;

public class Localisation {

    private static Languages currentLang = Languages.RU;

    public static Languages getCurrentLanguage() {
        return currentLang;
    }

    public static void setCurrentLang(Languages lang) {
        currentLang = lang;
    }

    public static String authorName() {
        if (currentLang == Languages.RU) return "Андрей Глейх";
        return "Andrey Gleykh";
    }

    public static String author(){
        if (currentLang == Languages.RU) return "Автор";
        return "Author";
    }

    public static String license(){
        if (currentLang == Languages.RU) return "Лицензия";
        return "License";
    }

    public static String licenseType(){
        return "GNU General Public License v3.0";
    }

    public static String version() {
        if (currentLang == Languages.RU) return "Версия ПО";
        return "Version";
    }

    public static String versionNumber() {
        return "0.4";
    }

    public static String http() {
        if (currentLang == Languages.RU) return "Сайт проекта";
        return "Web page";
    }

    public static String httpAddress() {
        return "https://github.com/saibogo/SimpleAlarmClock";
    }

    public static String selectDevice() {
        if (currentLang == Languages.RU) return "Выберите устройство";
        return "Select device";
    }

    public static String alarmClock() {
        if (currentLang == Languages.RU) return "Будильник";
        return "Alarm Clock";
    }

    public static String timer() {
        if (currentLang == Languages.RU) return "Таймер";
        return "Timer";
    }

    public static String stopWatch() {
        if (currentLang == Languages.RU) return "Секундомер";
        return "Stop Watch";
    }

    public static String exitString() {
        if (currentLang == Languages.RU) return "Выход";
        return "Exit";
    }

    public static String information() {
        if (currentLang == Languages.RU) return "О программе";
        return "About program";
    }

    public static String notCorrectedData() {
        if (currentLang == Languages.RU) return "Некорректные данные. Попробуйте еще раз!";
        return "Not corrected value(s). Try again!";
    }

    public static String stopString() {
        if (currentLang == Languages.RU) return "Остановить";
        return "Stop";
    }

    public static String startingStopWatch() {
        if (currentLang == Languages.RU) return "Запущен секундомер.";
        return "Starting Stop Watch";
    }

    public static String stopWatchValues() {
        if (currentLang == Languages.RU) return "Показания секундомера";
        return "Stop Watch Values";
    }

    public static String pressEnter() {
        if (currentLang == Languages.RU) return "Для остановки нажмите клавишу Ввод(Enter)";
        return "To stop press Enter key";
    }

    public static String stopWatchTimeScaled() {
        if (currentLang == Languages.RU) return "Часы, минуты, секунды, 1/10 секунды";
        return "Hours, minutes, seconds and 1/10 seconds";
    }

    public static String timePassed() {
        if (currentLang == Languages.RU) return "Прошло времени";
        return "Time passed";
    }

    public static String hours() {
        if (currentLang == Languages.RU) return "Часы";
        return "Hours";
    }

    public static String minutes() {
        if (currentLang == Languages.RU) return "Минуты";
        return "Minutes";
    }

    public static String seconds() {
        if (currentLang == Languages.RU) return "Секунды";
        return "Seconds";
    }

    public static String changeLanguage() {
        if (currentLang == Languages.RU) return "Сменить язык";
        return "Change Language";
    }

    public static String devicesName() {
        if (currentLang == Languages.RU) return "Имя устройства";
        return "Device name";
    }

    public static String howManyToTimer() {
        if (currentLang == Languages.RU) return  "Через какое время должен сработать таймер?";
        return "How many passed in this timer?";
    }

    public static String selectTimeToAlarmClock() {
        if (currentLang == Languages.RU) return "В какое время должен включиться будильник?";
        return "Select time to run Alarm Clock";
    }

    public static String noteInstrument() {
        if (currentLang == Languages.RU) return "Нота (от 0 до 131 включительно)";
        return "Note (in range 0...131)";
    }

    public static String instrumentNumber() {
        if (currentLang == Languages.RU) return "Номер музыкального инструмента (от 1 до 128 включительно)";
        return "Music instrument number (in range 1...128)";
    }

    public static String volume() {
        if (currentLang == Languages.RU) return "Громкость от 0 до 100%";
        return "Volume in range 0...100%";
    }

    public static String setupDeviceToTime(String name, Date date) {
        if (currentLang == Languages.RU) return "Устройство с именем " + name + " установлено на время " + date;
        return "Device " + name + " setup to time " + date;
    }

    public static String addTimeToAlarmClock(String name) {
        if (currentLang == Languages.RU) return "Отложить будильник " + name + " на 5 минут?(1 - да)";
        return "Add 5 minutes to Alarm Clock named " + name + "?(1 - yes)";
    }

    public static String addTimeToAlarmClockGui(String name) {
        if (currentLang == Languages.RU) return "Отложить будильник " + name + " на 5 минут?(1 - да)";
        return "Add 5 minutes to Alarm Clock named " + name + "?";
    }

    public static String newTimeToAlarmClock(String name, Date date) {
        if (currentLang == Languages.RU) return "Будильник " + name + " перенесен на " + date;
        return "New time to Alarm Clock " + name + " is " + date;
    }

    public static String addAlarmClock() {
        if (currentLang == Languages.RU) return "Добавить будильник";
        return "Add Alarm Clock";
    }

    public static String removeAlarmClock() {
        if (currentLang == Languages.RU) return "Удалить будильник";
        return "Remove Alarm Clock";
    }

    public static String alarmMessage() {
        if (currentLang == Languages.RU) return "Оповещение";
        return "Alarm message";
    }

    public static String create() {
        if (currentLang == Languages.RU) return "Создать";
        return "Create";
    }

    public static String notCorrectedTime() {
        if (currentLang == Languages.RU) return "Некорректно задано время!";
        return "Time not corrected!";
    }

    public static String timeTooSmall() {
        if (currentLang == Languages.RU) return "Дата и время должны быть больше текущего!";
        return "Date and time must be greater than the current!";
    }

    public static String cancel() {
        if (currentLang == Languages.RU) return "Отмена";
        return "Cancel";
    }

    public static String setupTime() {
        if (currentLang == Languages.RU) return "Задайте время";
        return "Select time";
    }

    public static String selectAlarmClockToRemove() {
        if (currentLang == Languages.RU) return "Выберите будильник для удаления";
        return "Select Alarm Clock to Remove";
    }

    public static String timerParameters() {
        if (currentLang == Languages.RU) return "Параметры таймера";
        return "Timer setup parameters";
    }

    public static String startTimer() {
        if (currentLang == Languages.RU) return "Запустить таймер";
        return "Start Timer";
    }

    public static String stopTimerSignal() {
        if (currentLang == Languages.RU) return "Остановить таймер";
        return "Stop Timer";
    }

    public static String timerSayBeep(String name) {
        if (currentLang == Languages.RU) return "Таймер " + name + " сработал!";
        return "Timer " + name + " is Running!";
    }

    public static String pleaseInterrupt() {
        if (currentLang == Languages.RU) return "Нажмите Ctrl + C для остановки";
        return "Precc Ctrl + C to stopping timer";
    }

    public enum Languages {
        RU, ENG
    }
}
