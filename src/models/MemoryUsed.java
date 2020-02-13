package models;

public class MemoryUsed {
    public MemoryUsed() {
    }

    public void show() {
        System.out.println("Memory used :" +
                (Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory()) + " bytes.");
    }
}
