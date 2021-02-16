package game;

import junit.framework.TestCase;

public class ResourceTest extends TestCase {
    private Island island;
    private Resource resource;
    private Faction capitalistes;
    private Faction loyalistes;

    @Override
    protected void setUp() throws Exception {
        resource = new Resource(500, 0);
        capitalistes = new Faction("capitalistes", 15, 10);
        loyalistes = new Faction("loyalistes", 15, 10);
        island = new Island(15, 15, GameDifficulty.NORMAL, resource);
        island.getPopulation().addFaction(capitalistes);
        island.getPopulation().addFaction(loyalistes);
    }

    public void testPayForCorruption() {
        island.corruptFaction(0, 1);
        assertEquals(350, island.getResource().getTreasury());
    }

    public void testPayForFood() {
        resource.buyFood(10);
        assertEquals(420, island.getResource().getTreasury());
    }

    public void testBuyFood() {
        resource.buyFood(10);
        assertEquals(10, resource.getFood());
    }
}
