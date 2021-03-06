package main.support;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.StringJoiner;


public class SupportClass {

    public static final String timeSeparator = ":";
    public static int normalInstrumentMaxNumber = 128;
    private static Image image = null;

    public static Color clockColor = new Color(169, 31, 24);
    public static Color alarmClockColor = new Color(35, 41, 169);
    public static Color buttonColor = new Color(169, 169, 169);
    public static Color panelColor = new Color(192, 192, 192);
    private static final String zeroString = "0";

    private  Font font;

    public SupportClass() {

        try {
            String pathToFonts = System.getProperty("user.home") + "/.fonts/electra.ttf";
            File file = new File(pathToFonts);
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, file).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            this.font = customFont;
        } catch (FontFormatException | IOException | NullPointerException e) {
            this.font = new Font(Font.MONOSPACED, Font.BOLD, 20);
        }

        try {
            String os = System.getProperty("os.name");
            String pathToIcon = System.getProperty("user.home");
            if (os.equals("Linux")) {
                pathToIcon = pathToIcon + "/.local/share/icons/alarmClock.png";
            } else {
                pathToIcon = pathToIcon + "/alarmClock.png";
            }
            File iconFile = new File(pathToIcon);
            ImageIcon imageIcon = new ImageIcon(String.valueOf(iconFile));
            image = imageIcon.getImage();
        } catch (Exception e) {
            image = null;
        }
    }


    public void setCustomUI() {
        UIManager.getDefaults().put("Label.font", font);

        UIManager.getDefaults().put("TextField.background", panelColor);
        UIManager.getDefaults().put("TextField.font", font);

        UIManager.getDefaults().put("Button.font", font);
        UIManager.getDefaults().put("Button.border", new SupportClass.RoundedBorder(10));
        UIManager.getDefaults().put("Button.background", SupportClass.buttonColor);

        UIManager.getDefaults().put("Panel.background", panelColor);
        UIManager.getDefaults().put("Panel.font", font);

        UIManager.getDefaults().put("Spinner.background", panelColor);
        UIManager.getDefaults().put("Spinner.font", font);

        UIManager.getDefaults().put("Slider.background", panelColor);
        UIManager.getDefaults().put("Slider.font", font);

        UIManager.getDefaults().put("OptionPane.background", panelColor);
        UIManager.getDefaults().put("OptionPane.font", font);

        UIManager.getDefaults().put("NumberEditor.background", panelColor);


    }

    public static Image getImageIcon() {
        return image;
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

    public static String timeToString3(long h, long m, long s) {
        StringJoiner joiner = new StringJoiner(timeSeparator);
        joiner.add(h > 9 ? " " + h : " " + zeroString + h);
        joiner.add(m > 9 ? "" + m : zeroString + m);
        joiner.add(s > 9 ? "" + s : zeroString + s + " ");
        return joiner.toString();
    }

    public static String timeToString4(long h, long m, long s, long ts) {
        return " "  + timeToString3(h, m, s).replace(" ", "") + timeSeparator + ts + " ";
    }
}
