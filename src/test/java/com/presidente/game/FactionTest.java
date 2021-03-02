package com.presidente.game;

import com.presidente.builders.IslandBuilder;
import com.presidente.builders.ResourceBuilder;
import junit.framework.TestCase;

public class FactionTest extends TestCase {
    private Faction capitalists;
    private Faction loyalists;
    private Island island;
    private Island islandWithoutMoney;

    @Override
    protected void setUp() {
        capitalists = new Faction("capitalistes", 50, 15);
        loyalists = new Faction("loyalistes", 50, 15);
        Population population = new Population();
        population.addFaction(capitalists);
        population.addFaction(loyalists);
        island = new IslandBuilder()
                .setPopulation(population)
                .setResource(new ResourceBuilder().setTreasury(1000).build())
                .build();
        islandWithoutMoney = new IslandBuilder()
                .setPopulation(population)
                .setResource(new ResourceBuilder().setTreasury(0).build())
                .build();
    }

    public void testCorruption() {
        island.corruptFaction(0, 1);
        assertEquals(60, capitalists.getSatisfaction());
    }

    public void testMultipleCorruption() {
        island.corruptFaction(0, 2);
        assertEquals(70, capitalists.getSatisfaction());
    }

    public void testCorruptionWithoutMoney() {
        islandWithoutMoney.corruptFaction(0, 1);
        assertEquals(50, capitalists.getSatisfaction());
    }

    public void testLoyalistSatisfactionAfterCorruption() {
        island.corruptFaction(0, 1);
        assertEquals(28, loyalists.getSatisfaction());
    }
}
