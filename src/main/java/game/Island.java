package game;

public class Island {
    private int agriculture;
    private int industrie;
    private final GameDifficulty difficulty;
    private final Ressource ressource;
    private final Population population;
    private final Season[] seasons;

    public Island(int agriculture, int industrie, GameDifficulty difficulty, Ressource ressource) {
        this.agriculture = agriculture;
        this.industrie = industrie;
        this.difficulty = difficulty;
        this.ressource = ressource;
        this.population = new Population();
        this.seasons = new Season[4];
    }

    public int getAgriculture() {
        return agriculture;
    }

    public int getIndustrie() {
        return industrie;
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

    public void setAgriculture(int agriculture) {
        this.agriculture = agriculture;
    }

    public void setIndustrie(int industrie) {
        this.industrie = industrie;
    }
}
