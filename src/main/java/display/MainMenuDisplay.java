package display;

import main.Main;

import java.io.IOException;
import java.net.URISyntaxException;

public class MainMenuDisplay extends MenuDisplay {
    public MainMenuDisplay(String choicesDisplay) {
        super(choicesDisplay, 3);
    }

    @Override
    protected boolean execute(int choice) throws IOException, URISyntaxException {
        switch (choice) {
            case 0: {
                GameCreationMenuDisplay gcd = new GameCreationMenuDisplay("0. Mode SandBox\n1. Mode Scénario");
                return gcd.displayMenu(Main.SCANNER);
            }
            case 1: {
                break;
            }
            case 2: {
                System.exit(0);
            }
        }
        return true;
    }
}
