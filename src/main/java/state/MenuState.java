package state;


import display.menu.main.MainMenu;

import java.io.IOException;
import java.net.URISyntaxException;

public class MenuState extends State {
    public MenuState(int id) {
        super(id);
    }

    @Override
    public void init() {

    }


    @Override
    public void run() throws IOException, URISyntaxException {
        MainMenu mmd = new MainMenu("0. Commencer une nouvelle partie\n1. Charger une partie\n2. Quitter le jeux");
        mmd.displayMenu();

    }

}
