package com.presidente.display.menu.game;

import com.presidente.display.MenuDisplay;
import com.presidente.game.Island;

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
            if (getSwitchSize() <= 1) {
                return false;
            }
            int amount = getChoice();
            island.corruptFaction(choice, amount);
        }
        return false;
    }
}
