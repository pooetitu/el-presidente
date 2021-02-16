package display.menu.game;

import display.MenuDisplay;
import game.Island;

public class FactionCorruptionMenu extends MenuDisplay {
    private final Island island;

    public FactionCorruptionMenu(Island island) {
        super(island.getPopulation().corruptionDisplay(), island.getPopulation().getFactionCount() + 1);
        this.island = island;
    }

    @Override
    protected boolean execute(int choice) {
        if (choice < getSwitchSize() - 1) {
            setChoicesDisplay("Combien de fois voulez-vous les corrompre ? (Achetable: " + island.getMaximumPurchasableCorruption(choice) + " )");
            setSwitchSize(island.getMaximumPurchasableCorruption(choice));
            int amount = getChoice();
            island.corruptFaction(choice, amount);
        }
        return false;
    }
}
