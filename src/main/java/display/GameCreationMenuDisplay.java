package display;

import game.GameDifficulty;
import game.Island;
import game.Ressource;
import main.Main;

public class GameCreationMenuDisplay extends MenuDisplay {

    public GameCreationMenuDisplay(String choicesDisplay) {
        super(choicesDisplay, 2);
    }

    @Override
    protected boolean execute(int choice) {
        switch (choice) {
            case 0: {
                Island isl = new Island(15,15,new Ressource(15,15));
                break;
            }
            case 1: {
                break;
            }
        }
        return true;
    }
}
