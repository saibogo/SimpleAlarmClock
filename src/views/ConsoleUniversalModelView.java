package views;

import models.clockModels.ClockUniversalModel;

import java.util.List;

public class ConsoleUniversalModelView {

    private ClockUniversalModel model;

    public ConsoleUniversalModelView(ClockUniversalModel model) {
        this.model = model;
    }

    private static String toTwoNumber(long num) {
        if (num > 9) return "" + num;
        return "0" + num;
    }

    public void show() {
        List<Long> data;
        int count = this.model.howManyElements();
        for (int i = 0; i < count; i++) {
            data = this.model.getTimeElement(i);
            System.out.println(toTwoNumber(data.get(0)) +
                    ":" + toTwoNumber(data.get(1)) + ":" + toTwoNumber(data.get(2)));
        }
    }
}
