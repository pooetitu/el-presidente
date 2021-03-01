package com.presidente.game;

import com.presidente.builders.IslandBuilder;
import com.presidente.builders.ResourceBuilder;
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
        resource = new ResourceBuilder().setTreasury(500).build();
        island = new IslandBuilder().setAgriculture(15).setIndustry(15).setResource(resource).setPopulation(population).build();
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

    public void testAddIndustryPayoff() {
        int initialTreasury = resource.getTreasury();
        resource.addIndustryPayoff(island.getIndustry());
        int receivedTreasury = resource.getTreasury() - initialTreasury;
        assertEquals(150, receivedTreasury);
    }

    public void testAddAgriculturePayoff() {
        int initialFood = resource.getFoodQuantity();
        resource.addAgriculturePayoff(island.getAgriculture(), 0);
        int receivedFood = resource.getFoodQuantity() - initialFood;
        assertEquals(600, receivedFood);
    }
}
