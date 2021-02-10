package game;

import com.thoughtworks.xstream.annotations.XStreamImplicit;
import display.DifficultyMenuDisplay;
import game.event.Event;
import main.Main;
import utils.GameLoader;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Island {
    @XStreamImplicit(itemFieldName = "event")
    private final Queue<Event> eventsQueue;
    private final GameDifficulty difficulty;
    private final Ressource ressource;
    private final Population population;

    @XStreamImplicit(itemFieldName = "season")
    private final Season[] seasons;
    private int agriculture;
    private int industrie;

    // New sandbox game constructor with selection of difficulty
    public Island(int agriculture, int industrie, Ressource ressource) throws IOException {
        this.agriculture = agriculture;
        this.industrie = industrie;
        this.ressource = ressource;
        this.difficulty = displayDifficultySelection();
        this.eventsQueue = new LinkedList<>();
        this.population = new Population();
        this.population.populate();
        this.seasons = GameLoader.getGameLoaderInstance().loadSeasons();
    }


    // New sandbox game constructor with predefined difficulty
    public Island(int agriculture, int industrie, GameDifficulty difficulty, Ressource ressource) throws IOException {
        this.agriculture = agriculture;
        this.industrie = industrie;
        this.ressource = ressource;
        this.difficulty = difficulty;
        this.eventsQueue = new LinkedList<>();
        this.population = new Population();
        this.population.populate();
        this.seasons = GameLoader.getGameLoaderInstance().loadSeasons();
    }


    // Load game from save or scenario constructor
    public Island(int agriculture, int industrie, GameDifficulty difficulty, Ressource ressource, Queue<Event> eventsQueue, Population population) throws IOException {
        this.agriculture = agriculture;
        this.industrie = industrie;
        this.difficulty = difficulty;
        this.ressource = ressource;
        this.eventsQueue = eventsQueue;
        this.population = population;
        this.seasons = GameLoader.getGameLoaderInstance().loadSeasons();
    }

    private GameDifficulty displayDifficultySelection() throws IOException {
        DifficultyMenuDisplay dmd = new DifficultyMenuDisplay("0. Facile\n1. Normal\n2. Difficile");
        dmd.displayMenu(Main.SCANNER);
        return dmd.getGameDifficulty();
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

    public Event getNextEvent(int currentSeasonIndex) {
        if (!eventsQueue.isEmpty()) {
            return eventsQueue.remove();
        }
        Event event = seasons[currentSeasonIndex].getRandomEvent();
        addNextEventToQueue(event.getNextEvent());
        return event;
    }

    private void addNextEventToQueue(Event event) {
        if (event != null) {
            eventsQueue.add(event);
            addNextEventToQueue(event.getNextEvent());
        }
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

    @Override
    public String toString() {
        return ressource + "\n" +
                String.format("%-21s%s", "agriculture: " + agriculture + "%",
                        "industrie: " + industrie + "%") + "\n" + "global satisfaction: " + population.getGlobalSatisfaction();
    }
}
