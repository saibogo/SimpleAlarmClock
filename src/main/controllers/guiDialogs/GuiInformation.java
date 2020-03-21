package main.controllers.guiDialogs;

import main.support.Localisation;
import main.support.SupportClass;

import javax.swing.*;

public class GuiInformation{

    public static void showProductionInfo() {
        JFrame infoFrame = new JFrame();
        infoFrame.setIconImage(SupportClass.getImageIcon());
        String msg = Localisation.author() + ": " + Localisation.authorName() + "\n" + Localisation.license() + ": " +
                Localisation.licenseType() + "\n" + Localisation.version() + ": " +
                Localisation.versionNumber() + "\n" + Localisation.http() + ": " + Localisation.httpAddress();
        JOptionPane.showMessageDialog(infoFrame, msg, Localisation.information(), JOptionPane.INFORMATION_MESSAGE);
    }
}
