package models;

public class AlarmExistsException extends Exception {

    public AlarmExistsException() {
        super("Alarm Clock already exist!");
    }
}
