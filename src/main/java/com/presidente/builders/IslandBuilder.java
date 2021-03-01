package com.presidente.builders;

import com.presidente.game.GameDifficulty;
import com.presidente.game.Island;
import com.presidente.game.Population;
import com.presidente.game.Resource;

public class IslandBuilder {
    private final Island island;

    public IslandBuilder() {
        this.island = new Island();
    }

    public IslandBuilder setDifficulty(GameDifficulty difficulty) {
        island.setDifficulty(difficulty);
        return this;
    }

    public IslandBuilder setResource(Resource resource) {
        island.setResource(resource);
        return this;
    }

    public IslandBuilder setAgriculture(int agriculture) {
        island.setAgriculture(agriculture);
        return this;
    }

    public IslandBuilder setIndustrie(int industrie) {
        island.setIndustrie(industrie);
        return this;
    }

    public IslandBuilder setPopulation(Population population) {
        island.setPopulation(population);
        return this;
    }

    public Island build() {
        return island;
    }
}
