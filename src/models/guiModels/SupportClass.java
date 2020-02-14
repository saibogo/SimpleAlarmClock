package models.guiModels;

import models.clockModels.ClockUniversalModel;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SupportClass {
/*
    private static File file = new File("./src/models/guiModels/electra.ttf");
    private static FileInputStream fis;

    static {
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Font FONT1;

    static {
        try {
            FONT1 = Font.createFont(Font.BOLD, fis);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 */
        public static Font FONT1 = new Font(Font.MONOSPACED, Font.BOLD, 20);


    public SupportClass() throws FileNotFoundException {
    }

    public static int calculateHeight(ClockUniversalModel model, Font font) {
        int result;
        if (model.howManyElements() > 1) result = (model.howManyElements() * 2 + 1);
        else result = model.howManyElements() * 4;
        return result * font.getSize(); //rows
    }

    public static int calculateWidth(ClockUniversalModel model, Font font) {
        return 10 * font.getSize(); // columns _hh:mm:ss_
    }
}
