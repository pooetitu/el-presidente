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

    /**
     * Set the percentage amount of agriculture on the Island
     *
     * @param agriculture The difficulty to be set to the Island
     * @return The instance of the current builder
     */
    public IslandBuilder setAgriculture(int agriculture) {
        island.setAgriculture(agriculture);
        return this;
    }

    /**
     * Set the percentage amount of industry on the Island
     *
     * @param industry The difficulty to be set to the Island
     * @return The instance of the current builder
     */
    public IslandBuilder setIndustry(int industry) {
        island.setIndustry(industry);
        return this;
    }

    /**
     * Set the difficulty of the Island
     *
     * @param difficulty The difficulty to be set to the Island
     * @return The instance of the current builder
     */
    public IslandBuilder setDifficulty(GameDifficulty difficulty) {
        island.setDifficulty(difficulty);
        return this;
    }

    /**
     * Set the instance of the Resource
     *
     * @param resource The instance of Resource to be added to the Island
     * @return The instance of the current builder
     */
    public IslandBuilder setResource(Resource resource) {
        island.setResource(resource);
        return this;
    }

    /**
     * Set the instance of the Population
     *
     * @param population The instance of Population to be added to the Island
     * @return The instance of the current builder
     */
    public IslandBuilder setPopulation(Population population) {
        island.setPopulation(population);
        return this;
    }

    /**
     * Set the turn of the Island
     *
     * @param turn The turn to be set
     * @return The instance of the current builder
     */
    public IslandBuilder setTurn(int turn) {
        island.setTurn(turn);
        return this;
    }

    /**
     * @return Return the built instance of Island
     */
    public Island build() {
        return island;
    }
}
