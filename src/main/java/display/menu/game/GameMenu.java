package display.menu.game;

import display.MenuDisplay;
import game.Island;
import utils.GameSaver;

public class GameMenu extends MenuDisplay {
    private final Island island;
    private final GameSaver gameSaver;

    public GameMenu(Island island) {
        super("0. Sauvegarder la partie\n1. Charger une sauvegarde\n2. Affichage plus détailler de l'ile\n3. Poursuivre la partie", 4);
        this.island = island;
        gameSaver = GameSaver.getGameSaver();
    }

    @Override
    protected boolean execute(int choice) {
        switch (choice) {
            case 0: {
                SaveMenu saveMenu = new SaveMenu(island);
                saveMenu.displayMenu();
                return false;
            }
            case 1: {
                LoadMenu loadMenu = new LoadMenu(gameSaver.showSaveList(), gameSaver.getSaveListCount());
                loadMenu.displayMenu();
                return false;
            }
            case 2: {
                System.out.println(island.advancedDisplay());
                break;
            }
            case 3: {
                return false;
            }
        }
        return true;
    }
}
