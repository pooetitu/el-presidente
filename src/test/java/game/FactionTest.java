package game;

import junit.framework.TestCase;

public class FactionTest extends TestCase {
    private Faction capitalistes;
    private Faction loyalistes;
    private Island island;
    private Island islandWithoutMoney;

    @Override
    protected void setUp() throws Exception {
        capitalistes = new Faction("capitalistes", 50, 15);
        loyalistes = new Faction("loyalistes", 50, 15);
        island = new Island(15, 15, GameDifficulty.NORMAL, new Ressource(1000, 10));
        island.getPopulation().addFaction(capitalistes);
        island.getPopulation().addFaction(loyalistes);
        islandWithoutMoney = new Island(15, 15, GameDifficulty.NORMAL, new Ressource(0, 10));
        islandWithoutMoney.getPopulation().addFaction(capitalistes);
        islandWithoutMoney.getPopulation().addFaction(loyalistes);
    }

    public void testCorruption() {
        capitalistes.takeCorruption(island);
        assertEquals(60, capitalistes.getSatisfaction());
    }

    public void testCorruptionWithoutMoney() {
        capitalistes.takeCorruption(islandWithoutMoney);
        assertEquals(50, capitalistes.getSatisfaction());
    }

    public void testLoyalistSatisfactionAfterCorruption() {
        capitalistes.takeCorruption(island);
        assertEquals(38, loyalistes.getSatisfaction());
    }
}
