package com.presidente.display.menu.game;

import com.presidente.display.MenuDisplay;
import com.presidente.game.Island;

public class EndYearMenu extends MenuDisplay {
    public final Island island;

    public EndYearMenu(Island island) {
        super("0. Acheter de la nourriture\n1. Soudoyer une faction\n2. Continuer la partie", 3);
        this.island = island;
    }

    @Override
    protected boolean execute(int choice) {
        switch (choice) {
            case 0: {
                BuyFoodMenu buyFoodMenu = new BuyFoodMenu(island.getResource());
                buyFoodMenu.displayMenu();
                break;
            }
            case 1: {
                FactionCorruptionMenu factionCorruptionMenu = new FactionCorruptionMenu(island);
                factionCorruptionMenu.displayMenu();
                break;
            }
            case 2: {
                return false;
            }
        }
        return true;
    }
}
