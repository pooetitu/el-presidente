package com.presidente.game;

import com.presidente.builders.IslandBuilder;
import com.presidente.builders.ResourceBuilder;
import junit.framework.TestCase;

public class IslandTest extends TestCase {
    private Island island;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        island = new IslandBuilder().setResource(new ResourceBuilder().build()).build();
    }

    public void testIsGameOver() {
    }

    public void testGetMaximumPurchasableCorruption() {
    }

    public void testIsEndOfYear() {
    }

    public void testEndTheYear() {
        island.setTurn(3);
        assertTrue(island.isEndOfYear());
    }

    public void testNewTurn() {
        int lastTurn = island.getTurn();
        island.newTurn();
        assertNotSame(lastTurn, island.getTurn());
    }
}