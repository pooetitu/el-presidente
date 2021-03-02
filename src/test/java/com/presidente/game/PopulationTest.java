package com.presidente.game;

import junit.framework.TestCase;

public class PopulationTest extends TestCase {
    private Population population;
    private Faction capitalists;
    private Faction communists;
    private Faction liberals;

    @Override
    protected void setUp() {
        population = new Population();
        capitalists = new Faction("capitalistes", 15, 15);
        communists = new Faction("communistes", 65, 35);
        liberals = new Faction("libéraux", 80, 2);
        population.addFaction(communists);
    }

    public void testGetFaction() {
        assertEquals(communists, population.getFactionByName(communists.getName()));
    }

    public void testAddFaction() {
        population.addFaction(capitalists);
        assertEquals(capitalists, population.getFactionByName(capitalists.getName()));
    }

    public void testGlobalSatisfactionOneFaction() {
        assertEquals(65, population.getGlobalSatisfaction());
    }

    public void testGlobalSatisfactionMultipleFaction() {
        population.addFaction(capitalists);
        population.addFaction(liberals);
        assertEquals(51, population.getGlobalSatisfaction());
    }

    public void testGetTotalPopulationOneFaction() {
        assertEquals(35, population.getTotalPopulation());
    }

    public void testGetTotalPopulationMultipleFaction() {
        population.addFaction(capitalists);
        assertEquals(50, population.getTotalPopulation());
    }

    public void testGetNotInitializedFaction() {
        assertEquals("Undefined", population.getFactionByName("Undefined").getName());
    }
}
