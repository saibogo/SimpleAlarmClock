package myException;

public class AlarmExistsException extends Exception {

    public AlarmExistsException() {
        super("Alarm Clock already exist!");
    }
}
