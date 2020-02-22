package main.views;

import main.clockModels.ClockUniversalModel;
import main.models.supportModels.Triplet;
import main.myException.TripletBuildException;


public class ConsoleUniversalModelView {

    private final ClockUniversalModel model;

    public ConsoleUniversalModelView(ClockUniversalModel model) {
        this.model = model;
    }

    private static String toTwoNumber(long num) {
        if (num > 9) return "" + num;
        return "0" + num;
    }

    public void show() throws TripletBuildException {
        Triplet<Long> data;
        int count = this.model.howManyElements();
        for (int i = 0; i < count; i++) {
            data = this.model.getTimeElement(i);
            System.out.println(toTwoNumber(data.getFirst()) +
                    ":" + toTwoNumber(data.getSecond()) + ":" + toTwoNumber(data.getLast()));
        }
    }
}
