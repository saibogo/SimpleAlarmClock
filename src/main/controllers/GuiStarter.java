package main.controllers;

import main.controllers.guiControllers.SupportClass;
import main.controllers.guiDialogs.MainSelectedMenu;

public class GuiStarter {

    public static void startGui() {
        SupportClass supportClass = new SupportClass();
        supportClass.setCustomUI();

        new MainSelectedMenu();
    }
}
