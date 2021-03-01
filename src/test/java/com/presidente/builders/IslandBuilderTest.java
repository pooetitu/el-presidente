package com.presidente.builders;

import com.presidente.game.GameDifficulty;
import com.presidente.game.Island;
import com.presidente.game.Population;
import com.presidente.game.Resource;
import junit.framework.TestCase;

public class IslandBuilderTest extends TestCase {
    private IslandBuilder builder;

    public void setUp() throws Exception {
        super.setUp();
        builder = new IslandBuilder();
    }

    public void testBuild() {
        Island island = builder.build();
        assertNotNull(island);
    }

    public void testSetDifficulty() {
        Island island = builder.setDifficulty(GameDifficulty.EASY).build();
        assertEquals(GameDifficulty.EASY, island.getDifficulty());
    }

    public void testSetResource() {
        Resource resource = new Resource();
        Island island = builder.setResource(resource).build();
        assertEquals(resource, island.getResource());
    }

    public void testSetAgriculture() {
        Island island = builder.setAgriculture(15).build();
        assertEquals(15, island.getAgriculture());
    }

    public void testSetIndustrie() {
        Island island = builder.setIndustrie(15).build();
        assertEquals(15, island.getIndustrie());
    }

    public void testSetPopulation() {
        Population population = new Population();
        Island island = builder.setPopulation(population).build();
        assertEquals(population, island.getPopulation());
    }
}