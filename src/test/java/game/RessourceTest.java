package game;

import junit.framework.TestCase;

public class RessourceTest extends TestCase {
    private Island island;
    private Ressource ressource;
    private Faction capitalistes;
    private Faction loyalistes;

    @Override
    protected void setUp() throws Exception {
        ressource = new Ressource(500, 0);
        capitalistes = new Faction("capitalistes", 15, 10);
        loyalistes = new Faction("loyalistes", 15, 10);
        island = new Island(15, 15, GameDifficulty.NORMAL, ressource);
        island.getPopulation().addFaction(capitalistes);
        island.getPopulation().addFaction(loyalistes);
    }

    public void testPayForCorruption() {
        island.corruptFaction(0);
        assertEquals(275, island.getRessources().getTreasury());
    }

    public void testPayForFood() {
        ressource.buyFood(10);
        assertEquals(420, island.getRessources().getTreasury());
    }

    public void testBuyFood() {
        ressource.buyFood(10);
        assertEquals(10, ressource.getFood());
    }
}
