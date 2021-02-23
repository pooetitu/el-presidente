package com.presidente.game;

import junit.framework.TestCase;

public class PopulationTest extends TestCase {
    private Population population;
    private Faction capitalistes;
    private Faction communistes;
    private Faction liberaux;

    @Override
    protected void setUp() {
        population = new Population();
        capitalistes = new Faction("capitalistes", 15, 15);
        communistes = new Faction("communistes", 65, 35);
        liberaux = new Faction("libéraux", 80, 2);
        population.addFaction(communistes);
    }

    public void testGetFaction() {
        assertEquals(communistes, population.getFactionByName(communistes.getName()));
    }

    public void testAddFaction() {
        population.addFaction(capitalistes);
        assertEquals(capitalistes, population.getFactionByName(capitalistes.getName()));
    }

    public void testGlobalSatisfactionOneFaction() {
        assertEquals(65, population.getGlobalSatisfaction());
    }

    public void testGlobalSatisfactionMultipleFaction() {
        population.addFaction(capitalistes);
        population.addFaction(liberaux);
        assertEquals(51, population.getGlobalSatisfaction());
    }

    public void testGetTotalPopulationOneFaction() {
        assertEquals(35, population.getTotalPopulation());
    }

    public void testGetTotalPopulationMultipleFaction() {
        population.addFaction(capitalistes);
        assertEquals(50, population.getTotalPopulation());
    }
}
