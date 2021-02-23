package com.presidente.display.menu.game;

import com.presidente.display.MenuDisplay;
import com.presidente.game.Resource;

public class BuyFoodMenu extends MenuDisplay {
    private final Resource resource;

    public BuyFoodMenu(Resource resource) {
        super("Saisir la quantité de nourriture à acheter (Achetable: " + resource.purchasableMaximumFoodAmount() + ")", resource.purchasableMaximumFoodAmount() + 1);
        this.resource = resource;
    }

    @Override
    protected boolean execute(int choice) {
        if (getSwitchSize() <= 1) {
            return false;
        }
        resource.buyFood(choice);
        return false;
    }
}
