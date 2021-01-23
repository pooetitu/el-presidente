package game;

import junit.framework.TestCase;

public class PopulationTest extends TestCase {
    private Population population;
    private Faction capitalistes;
    private Faction communistes;

    @Override
    protected void setUp() throws Exception {
        population = new Population();
        capitalistes = new Faction("capitalistes", 15, 15);
        communistes = new Faction("communistes", 15, 15);
        population.addFaction(communistes);
    }

    public void testGetFaction() {
        assertEquals(communistes, population.getFactionByName(communistes.getName()));
    }

    public void testAddFaction() {
        population.addFaction(capitalistes);
        assertEquals(capitalistes, population.getFactionByName(capitalistes.getName()));
        assertEquals(population, capitalistes.getPopulation());
    }

    public void testCorruptionDisplay() {
        assertEquals("0. Retour\n" +
                "1. communistes - 225", population.corruptionDisplay());
    }
}
