package display.menu.game;

import display.MenuDisplay;
import game.Island;
import main.Main;
import utils.GameSaver;

import java.io.IOException;
import java.net.URISyntaxException;

public class SaveMenu extends MenuDisplay {
    private final Island island;
    private final GameSaver gameSaver;
    private final ChooseSaveMenu saveMenu;

    public SaveMenu(Island island) {
        super("0. Créer une nouvelle sauvegarde\n1. Écraser une ancienne sauvegarde", 3);
        gameSaver = GameSaver.getGameSaver();
        this.island = island;
        this.saveMenu = new ChooseSaveMenu(gameSaver.showSaveList(), gameSaver.getSaveListCount(), island);
    }

    @Override
    protected boolean execute(int choice) throws IOException, URISyntaxException {
        switch (choice) {
            case 0: {
                System.out.print("Saisissez le nom de la sauvegarde: ");
                String saveName = Main.SCANNER.next();
                gameSaver.createSaveFile(saveName, island);
                break;
            }
            case 1: {
                saveMenu.displayMenu();
                break;
            }
        }
        return true;
    }
}
