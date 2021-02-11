package display.menu.game;

import display.MenuDisplay;
import game.Island;
import utils.GameSaver;

import java.io.IOException;
import java.net.URISyntaxException;

public class GameMenu extends MenuDisplay {
    private final Island island;
    private final GameSaver gameSaver;

    public GameMenu(Island island) {
        super("0. Sauvegarder la partie\n1. Charger une sauvegarde\n2. Affichage plus détailler de l'ile\n3. Poursuivre la partie", 4);
        this.island = island;
        gameSaver = GameSaver.getGameSaver();
    }

    @Override
    protected boolean execute(int choice) throws IOException, URISyntaxException {
        switch (choice) {
            case 0: {
                SaveMenu saveMenu = new SaveMenu(island);
                saveMenu.displayMenu();
                break;
            }
            case 1: {
                LoadMenu loadMenu = new LoadMenu(gameSaver.showSaveList(), gameSaver.getSaveListCount());
                loadMenu.displayMenu();
                break;
            }
            case 2: {
                System.out.println("print détailler de l'ile");
                break;
            }
        }
        return false;
    }
}
