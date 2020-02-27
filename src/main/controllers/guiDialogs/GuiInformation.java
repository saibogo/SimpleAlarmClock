package main.controllers.guiDialogs;

import javax.swing.*;
import java.awt.*;

public class GuiInformation{

    private static String author = "Андрей Глейх";
    private static String license = "GNU General Public License v3.0";
    private static String version = "0.2";
    private static String http = "https://github.com/saibogo/SimpleAlarmClock";

    public static void showProductionInfo() {
        String msg = "Автор: " + author + "\nЛицензия: " + license + "\nВерсия: " + version + "\nСайт: " + http;
        JOptionPane.showMessageDialog(new Frame(), msg, "О программе", JOptionPane.INFORMATION_MESSAGE);
    }
}
