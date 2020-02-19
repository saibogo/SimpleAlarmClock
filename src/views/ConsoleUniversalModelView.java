package views;

import myException.TripletBuildException;
import models.clockModels.ClockUniversalModel;
import models.supportModels.Triplet;


public class ConsoleUniversalModelView {

    private ClockUniversalModel model;

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
