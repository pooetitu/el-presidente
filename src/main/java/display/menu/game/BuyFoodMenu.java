package display.menu.game;

import display.MenuDisplay;
import game.Resource;

public class BuyFoodMenu extends MenuDisplay {
    private final Resource resource;

    public BuyFoodMenu(Resource resource) {
        super("Saisir la quantité de nourriture à acheter (Achetable: " + resource.purchasableMaximumFoodAmount() + ")", resource.purchasableMaximumFoodAmount() + 1);
        this.resource = resource;
    }

    @Override
    protected boolean execute(int choice) {
        resource.buyFood(choice);
        return false;
    }
}