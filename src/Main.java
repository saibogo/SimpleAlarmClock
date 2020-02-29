import main.controllers.GuiStarter;
import main.controllers.consoleControllers.ConsoleMainMenu;
import main.support.Localisation;

public class Main {

    public static void main(String[] args) {

        for (String arg: args) {
            System.out.println("Found argument " + arg);
            if (arg.equals("--help")) {
                System.out.println("--help Start this help\n--console Start program in console mode" +
                        "\n--info Product`s Information\n --en --ru Set localisation(Default Ru)");
            }

            if (arg.equals("--ru")) Localisation.setCurrentLang(Localisation.Languages.RU);

            if (arg.equals("--en")) Localisation.setCurrentLang(Localisation.Languages.ENG);

            if (arg.equals("--console")) {
                ConsoleMainMenu.selectClockType();
            }
            if (arg.equals("--info")) {
                String msg = Localisation.author() + ": " + Localisation.authorName() + "\n" +
                        Localisation.license() + ": " +
                        Localisation.licenseType() + "\n" + Localisation.version() + ": " +
                        Localisation.versionNumber() + "\n" + Localisation.http() + ": " + Localisation.httpAddress();
                System.out.println(msg);
            }
        }

        if (args.length == 0) GuiStarter.startGui();

    }
}
