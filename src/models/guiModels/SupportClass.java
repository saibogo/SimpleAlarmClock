package models.guiModels;

import models.clockModels.ClockUniversalModel;

import java.awt.*;

public class SupportClass {

    public static Font FONT1 = new Font(Font.SERIF, Font.BOLD, 20);

    public static int calculateHeight(ClockUniversalModel model, Font font) {
        return (model.howManyElements() * 3)* font.getSize(); //rows
    }

    public static int calculateWidth(ClockUniversalModel model, Font font) {
        return 10 * font.getSize(); // columns _hh:mm:ss_
    }
}
