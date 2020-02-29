package main.controllers.guiDialogs;

import main.support.Localisation;

import javax.swing.*;

public class GuiInformation{

    public static void showProductionInfo() {
        String msg = Localisation.author() + ": " + Localisation.authorName() + "\n" + Localisation.license() + ": " +
                Localisation.licenseType() + "\n" + Localisation.version() + ": " +
                Localisation.versionNumber() + "\n" + Localisation.http() + ": " + Localisation.httpAddress();
        JOptionPane.showMessageDialog(new JFrame(), msg, Localisation.information(), JOptionPane.INFORMATION_MESSAGE);
    }
}
