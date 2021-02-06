package display;

import main.Main;

public class MainMenuDisplay extends MenuDisplay {
    public MainMenuDisplay(String choicesDisplay) {
        super(choicesDisplay, 3);
    }

    @Override
    protected boolean execute(int choice) {
        switch (choice) {
            case 0: {
                GameCreationMenuDisplay gcd = new GameCreationMenuDisplay("0. Mode SandBox\n1. Mode Scénario");
                gcd.displayMenu(Main.SCANNER);
                break;
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
