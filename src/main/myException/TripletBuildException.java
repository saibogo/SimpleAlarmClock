package main.myException;

public class TripletBuildException extends Exception {

    public TripletBuildException() {
        super("Not found one or more element to create triplet!");
    }
}
