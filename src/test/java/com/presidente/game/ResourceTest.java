package com.presidente.game;

import com.presidente.builders.IslandBuilder;
import junit.framework.TestCase;

public class ResourceTest extends TestCase {
    private Island island;
    private Resource resource;

    @Override
    protected void setUp() {
        Faction capitalistes = new Faction("capitalistes", 15, 10);
        Faction loyalistes = new Faction("loyalistes", 15, 10);
        Population population = new Population();
        population.addFaction(capitalistes);
        population.addFaction(loyalistes);
        resource = new Resource(500);
        island = new IslandBuilder().setResource(resource).setPopulation(population).build();
    }

    public void testPayForCorruption() {
        island.corruptFaction(0, 1);
        assertEquals(350, island.getResource().getTreasury());
    }

    public void testPayForFood() {
        resource.buyFood(10, 0);
        assertEquals(420, island.getResource().getTreasury());
    }

    public void testBuyFood() {
        resource.buyFood(10, 0);
        assertEquals(10, resource.getFoodQuantity());
    }
}
