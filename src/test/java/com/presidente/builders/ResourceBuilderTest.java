package com.presidente.builders;

import com.presidente.game.Resource;
import junit.framework.TestCase;

public class ResourceBuilderTest extends TestCase {
    private ResourceBuilder builder;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        builder = new ResourceBuilder();
    }

    public void testClone() {
        Resource resource = new ResourceBuilder().setTreasury(10).addFood(100, 1).build();
        Resource clone = new ResourceBuilder().clone(resource).build();
        assertEquals(clone.getTreasury(), resource.getTreasury());
        assertEquals(clone.getFoodQuantity(), resource.getFoodQuantity());
    }

    public void testAddFood() {
        Resource resource = builder.addFood(100, 0).build();
        assertEquals(100, resource.getFoodQuantity());
    }

    public void testSetTreasury() {
        Resource resource = builder.setTreasury(100).build();
        assertEquals(100, resource.getTreasury());
    }

    public void testBuild() {
        Resource island = builder.build();
        assertNotNull(island);
    }
}