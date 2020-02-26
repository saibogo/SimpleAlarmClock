package main.controllers.guiControllers;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class SupportClass {

    private  Font font;

    public SupportClass() {

        try {

            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("./main/resources/electra.ttf").getFile());
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, file).deriveFont(18f);
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

    public void setCustomUI() {
        UIManager.getDefaults().put("Button.font", font);
        UIManager.getDefaults().put("Label.font", font);
        UIManager.getDefaults().put("Panel.font", font);
        UIManager.getDefaults().put("TextField.font", font);
        UIManager.getDefaults().put("Spinner.font", font);
        UIManager.getDefaults().put("Slider.font", font);
        UIManager.getDefaults().put("OptionPane.font", font);

        UIManager.getDefaults().put("Button.border", new SupportClass.RoundedBorder(10));
    }

    public static class RoundedBorder implements Border {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }


        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }


        public boolean isBorderOpaque() {
            return true;
        }


        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }
}
