package main.controllers.guiDialogs;

import main.controllers.guiControllers.SupportClass;

import javax.swing.*;
import java.awt.*;

public class GuiInformation{



    public static void showProductionInfo() {
        String msg = "Автор: " + SupportClass.author + "\nЛицензия: " +
                SupportClass.license + "\nВерсия: " +
                SupportClass.version + "\nСайт: " + SupportClass.http;
        JOptionPane.showMessageDialog(new Frame(), msg, "О программе", JOptionPane.INFORMATION_MESSAGE);
    }
}
