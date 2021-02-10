package state;

import game.Island;
import game.Season;
import game.event.Event;
import main.Main;

public class GameState extends State {
    private Island island;
    private int turn;

    public GameState(int id) {
        super(id);
    }

    @Override
    public void init() {
        turn = 0;
    }

    public void initGame(Island island) {
        this.island = island;
    }

    @Override
    public void run() {
        startEvent();
        displayIsland();
        checkGameOver();
        turn++;
    }

    private void displayIsland() {
        System.out.println(island);
        System.out.printf("%-20s%s%n%n", "Saison: " + Season.getSeason(turn % 4), "Année: " + turn / 4);
        System.out.println("Appuyer sur une touche pour poursuivre");
        Main.SCANNER.nextLine();
    }

    private void checkGameOver() {
        if (island.isGameOver()) {
            System.out.println("Game Over!\nVous n'avez malheureusement pas réussi à maintenir votre règne presidente.\nLa partie a duré " + turn / 4 + " années");
            System.out.println("Appuyer sur une touche pour retourner au menu principal");
            Main.SCANNER.nextLine();
            State.setActiveStateId(State.MENU_STATE_ID);
        }
    }

    private void startEvent() {
        Event event = island.getNextEvent(turn % 4);
        event.applyChoice(island, getUserChoice(event.display(island.getDifficulty().getEffectRatio()), event.getChoicesCount()));
    }

    private int getUserChoice(String display, int limit) {
        int choice = -1;
        while (choice < 0 || choice >= limit) {
            System.out.println(display);
            while (!Main.SCANNER.hasNextInt()) {
                System.out.println(display);
                Main.SCANNER.next();
            }
            choice = Main.SCANNER.nextInt();
            Main.SCANNER.nextLine();
        }
        return choice;
    }

}
