package models.guiModels;

import models.clockModels.ClockUniversalModel;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class SupportClass {

    public static Font FONT1;

    static {
        try {

            Font customFont = Font.createFont(Font.TRUETYPE_FONT,
                    new File("src/Fonts/electra.ttf")).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            FONT1 = customFont;
        } catch (FontFormatException | IOException e) {
            FONT1 = new Font(Font.MONOSPACED, Font.BOLD, 20);
        }

    }


    public static int calculateHeight(ClockUniversalModel model, Font font) {
        int result;
        if (model.howManyElements() > 1) result = (model.howManyElements() * 2 + 3);
        else result = model.howManyElements() * 6;
        return result * font.getSize(); //rows
    }

    public static int calculateWidth(Font font) {
        return 10 * font.getSize(); // columns _hh:mm:ss_
    }
}
