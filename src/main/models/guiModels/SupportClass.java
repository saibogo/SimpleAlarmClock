package main.models.guiModels;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class SupportClass {

    private  Font font;

    public SupportClass() {

        try {

            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("./main/resources/electra.ttf").getFile());
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, file).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            this.font = customFont;
        } catch (FontFormatException | IOException | NullPointerException e) {
            this.font = new Font(Font.MONOSPACED, Font.BOLD, 20);
        }
    }

    public Font getFont() {
        return font;
    }
}
