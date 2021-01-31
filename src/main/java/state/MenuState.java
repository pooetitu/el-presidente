package state;

public class MenuState extends State {
    public MenuState(int id) {
        super(id);
    }

    @Override
    public void run() {
        System.out.println("0. Commencer une nouvelle partie\n1. Charger une partie\n2. Quitter le jeux");
    }

}
