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
        communistes = new Faction("communistes", 15, 16);
        population.addFaction(communistes);
    }

    public void testGetFaction() {
        assertEquals(communistes, population.getFactionByName(communistes.getName()));
    }

    public void testAddFaction() {
        population.addFaction(capitalistes);
        assertEquals(capitalistes, population.getFactionByName(capitalistes.getName()));
    }

    public void testGetTotalPopulationOneFaction(){
        assertEquals(16, population.getTotalPopulation());
    }

    public void testGetTotalPopulationMultipleFaction(){
        population.addFaction(capitalistes);
        assertEquals(31, population.getTotalPopulation());
    }

    public void testCorruptionDisplayOneFaction() {
        assertEquals("0. Retour\n" +
                "1. communistes - 240", population.corruptionDisplay());
    }

    public void testCorruptionDisplayMultipleFaction() {
        population.addFaction(capitalistes);
        assertEquals("0. Retour\n" +
                "1. communistes - 240\n" +
                "2. capitalistes - 225", population.corruptionDisplay());
    }
}
