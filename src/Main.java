import main.controllers.GuiStarter;
import main.controllers.consoleControllers.ConsoleMainMenu;
import main.controllers.guiControllers.SupportClass;

public class Main {

    public static void main(String[] args) {

        for (String arg: args) {
            System.out.println("Found argument " + arg);
            if (arg.equals("--help")) {
                System.out.println("--help Start this help\n--console Start program in console mode" +
                        "\n--info Product`s Information");
            }
            if (arg.equals("--console")) {
                ConsoleMainMenu.selectClockType();
            }
            if (arg.equals("--info")) {
                String msg = "Автор: " + SupportClass.author + "\nЛицензия: " +
                        SupportClass.license + "\nВерсия: " +
                        SupportClass.version + "\nСайт: " + SupportClass.http;
                System.out.println(msg);
            }
        }

        if (args.length == 0) GuiStarter.startGui();

    }
}
