package game;

import display.DifficultyMenuDisplay;
import game.event.Event;
import main.Main;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Island {
    private final Queue<Event> eventsQueue;
    private final GameDifficulty difficulty;
    private final Ressource ressource;
    private final Population population;
    private final Season[] seasons;
    private int agriculture;
    private int industrie;

    // New sandbox game constructor with selection of difficulty
    public Island(int agriculture, int industrie, Ressource ressource) {
        this.agriculture = agriculture;
        this.industrie = industrie;
        this.ressource = ressource;
        this.difficulty = displayDifficultySelection();
        this.eventsQueue = new LinkedList<>();
        this.population = new Population();
        this.population.populate();
        this.seasons = new Season[4];
        System.out.println(this);
    }


    // New sandbox game constructor with predefined difficulty
    public Island(int agriculture, int industrie, GameDifficulty difficulty, Ressource ressource) {
        this.agriculture = agriculture;
        this.industrie = industrie;
        this.ressource = ressource;
        this.difficulty = difficulty;
        this.eventsQueue = new LinkedList<>();
        this.population = new Population();
        this.population.populate();
        this.seasons = new Season[4];
    }



    // Load game from save or scenario constructor
    public Island(int agriculture, int industrie, GameDifficulty difficulty, Ressource ressource, Queue<Event> eventsQueue, Population population) {
        this.agriculture = agriculture;
        this.industrie = industrie;
        this.difficulty = difficulty;
        this.ressource = ressource;
        this.eventsQueue = eventsQueue;
        this.population = population;
        this.seasons = createSeasons();
    }

    private GameDifficulty displayDifficultySelection() {
        DifficultyMenuDisplay dmd = new DifficultyMenuDisplay("0. Facile\n1. Normal\n2. Difficile");
        dmd.displayMenu(Main.SCANNER);
        return dmd.getGameDifficulty();
    }

    public Season[] createSeasons() {
        return null;
    }

    public void corruptFaction(int factionIndex) {
        if (ressource.getTreasury() <= 0) return;
        int corruptCost = ressource.getTreasury() - population.corruptFaction(factionIndex);
        ressource.setTreasury(corruptCost);
    }

    public int getAgriculture() {
        return agriculture;
    }

    public void setAgriculture(int agriculture) {
        if (this.industrie + agriculture >= 100)
            this.agriculture = 100 - this.industrie;
        else
            this.agriculture = agriculture;
    }

    public int getIndustrie() {
        return industrie;
    }

    public void setIndustrie(int industrie) {
        if (this.agriculture + industrie >= 100)
            this.industrie = 100 - this.agriculture;
        else
            this.industrie = industrie;
    }

    public Population getPopulation() {
        return population;
    }

    public Ressource getRessources() {
        return ressource;
    }

    public GameDifficulty getDifficulty() {
        return difficulty;
    }

    public Season[] getSeasons() {
        return seasons;
    }

    public Queue<Event> getEventsQueue() {
        return eventsQueue;
    }

    @Override
    public String toString() {
        return "Island{" +
                "eventsQueue=" + eventsQueue +
                ", difficulty=" + difficulty +
                ", ressource=" + ressource +
                ", population=" + population +
                ", seasons=" + Arrays.toString(seasons) +
                ", agriculture=" + agriculture +
                ", industrie=" + industrie +
                '}';
    }
}
