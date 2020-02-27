import main.controllers.guiDialogs.MainSelectedMenu;
import main.controllers.guiControllers.SupportClass;


public class Main {

    public static void main(String[] args) {

        SupportClass supportClass = new SupportClass();
        supportClass.setCustomUI();

        new MainSelectedMenu();


    }
}
