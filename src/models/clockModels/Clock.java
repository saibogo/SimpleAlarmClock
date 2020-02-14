package models.clockModels;

import java.util.Date;

public class Clock {

    private String name;
    private Date date;

    public Clock(final String name) {
        this.name = name;
        this.date = new Date();
    }

    public String getName() {
        return this.name;
    }

    private void dateUpdate(){
        date = new Date();
    }

    public int getSecond() {
        this.dateUpdate();
        return date.getSeconds();
    }

    public int getMinutes() {
        this.dateUpdate();
        return date.getMinutes();
    }

    public int getHours() {
        this.dateUpdate();
        return date.getHours();
    }
}
