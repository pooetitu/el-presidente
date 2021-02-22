package com.presidente.state;

import com.presidente.display.menu.game.EndYearMenu;
import com.presidente.display.menu.game.GameMenu;
import com.presidente.game.Island;
import com.presidente.game.Season;
import com.presidente.game.event.Event;
import com.presidente.main.Main;

public class GameState extends State {
    private Island island;
    private GameMenu gameMenuDisplay;
    private EndYearMenu endYearMenu;

    public GameState(int id) {
        super(id);
    }

    @Override
    public void init() {
    }

    public void init(Island island) {
        this.island = island;
        gameMenuDisplay = new GameMenu(island);
        endYearMenu = new EndYearMenu(island);
    }

    @Override
    public void run() {
        boolean displaying = true;
        startEvent();
        displayIsland();
        checkGameOver();
        island.newTurn();
        if (island.isEndOfYear()) {
            while (displaying) {
                displaying = endYearMenu.displayMenu();
            }
            island.endTheYear();
        }
        displaying = true;
        while (displaying) {
            displaying = gameMenuDisplay.displayMenu();
        }
    }

    private void displayIsland() {
        System.out.println(island);
        System.out.printf("%-20s%s%n%n", "Saison: " + Season.getSeason(island.getTurn() % 4), "Année: " + island.getTurn() / 4);
        System.out.println("Appuyer sur une touche pour poursuivre");
        Main.SCANNER.nextLine();
    }

    private void checkGameOver() {
        if (island.isGameOver()) {
            System.out.println("Game Over!\nVous n'avez malheureusement pas réussi à maintenir votre règne presidente.\nLa partie a duré " + island.getTurn() / 4 + " années");
            System.out.println("Appuyer sur une touche pour retourner au menu principal");
            Main.SCANNER.nextLine();
            State.setActiveStateId(State.MENU_STATE_ID);
        }
    }

    private void startEvent() {
        Event event = island.getNextEvent();
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
