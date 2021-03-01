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
        resource = new ResourceBuilder().setTreasury(500).addFood(100, 0).build();
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
        assertEquals(110, resource.getFoodQuantity());
    }

    public void testAddIndustryPayoff() {
        resource.addIndustryPayoff(island.getIndustry());
        assertEquals(650, resource.getTreasury());
    }

    public void testAddAgriculturePayoff() {
        resource.addAgriculturePayoff(island.getAgriculture(), 0);
        assertEquals(700, resource.getFoodQuantity());
    }

    public void testAddFood() {
        resource.addFood(100, 0);
        assertEquals(200, resource.getFoodQuantity());
    }

    public void testRemoveFood() {
        resource.removeFood(100);
        assertEquals(0, resource.getFoodQuantity());
    }

    public void testConsumeFood() {
        resource.consumeFood(10);
        assertEquals(60, resource.getFoodQuantity());
    }

    public void testRemoveExpiredFood() {
        resource.removeExpiredFood(0);
        assertEquals(0, resource.getFoodQuantity());
    }

    public void testPurchasableMaximumFoodAmount() {
        assertEquals(62, resource.purchasableMaximumFoodAmount());
    }
}
