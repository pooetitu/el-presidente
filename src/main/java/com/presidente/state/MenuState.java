package com.presidente.state;


import com.presidente.display.menu.main.MainMenu;

public class MenuState extends State {
    public MenuState(int id) {
        super(id);
    }

    @Override
    public void init() {

    }


    @Override
    public void run() {
        MainMenu mmd = new MainMenu("0. Commencer une nouvelle partie\n1. Charger une partie\n2. Quitter le jeux");
        mmd.displayMenu();

    }

}
