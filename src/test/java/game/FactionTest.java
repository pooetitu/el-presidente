package game;

import junit.framework.TestCase;

public class FactionTest extends TestCase {
    private Faction capitalistes;
    private Faction loyalistes;
    private Island island;
    private Island islandWithoutMoney;

    @Override
    protected void setUp() {
        capitalistes = new Faction("capitalistes", 50, 15);
        loyalistes = new Faction("loyalistes", 50, 15);
        island = new Island(15, 15, GameDifficulty.NORMAL, new Resource(1000, 10));
        island.getPopulation().addFaction(capitalistes);
        island.getPopulation().addFaction(loyalistes);
        islandWithoutMoney = new Island(15, 15, GameDifficulty.NORMAL, new Resource(0, 10));
        islandWithoutMoney.getPopulation().addFaction(capitalistes);
        islandWithoutMoney.getPopulation().addFaction(loyalistes);
    }

    public void testCorruption() {
        island.corruptFaction(0, 1);
        assertEquals(60, capitalistes.getSatisfaction());
    }
    public void testMultipleCorruption() {
        island.corruptFaction(0, 2);
        assertEquals(70, capitalistes.getSatisfaction());
    }
    public void testCorruptionWithoutMoney() {
        islandWithoutMoney.corruptFaction(0, 1);
        assertEquals(50, capitalistes.getSatisfaction());
    }

    public void testLoyalistSatisfactionAfterCorruption() {
        island.corruptFaction(0, 1);
        assertEquals(28, loyalistes.getSatisfaction());
    }
}
