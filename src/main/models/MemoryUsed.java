package main.models;

public class MemoryUsed {
    public MemoryUsed() {
    }

    public void show() {
        System.out.println("Memory used :" +
                String.format("%,d", Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()) +
                " bytes.");
    }
}
