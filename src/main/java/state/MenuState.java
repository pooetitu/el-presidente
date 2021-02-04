package state;


import display.MainMenuDisplay;

public class MenuState extends State {
    public MenuState(int id) {
        super(id);
    }

    @Override
    public void init() {

    }


    @Override
    public void run() {
        MainMenuDisplay mmd = new MainMenuDisplay("0. Commencer une nouvelle partie\n1. Charger une partie\n2. Quitter le jeux");
        boolean running = true;
        while (running) {
            running = mmd.displayMenu(scanner);
        }
    }

}
