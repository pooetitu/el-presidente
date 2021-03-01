package com.presidente.builders;

import com.presidente.game.Food;
import com.presidente.game.Resource;

import java.util.Map;

public class ResourceBuilder {
    private final Resource resource;

    public ResourceBuilder() {
        this.resource = new Resource();
    }

    public ResourceBuilder clone(Resource resource) {
        ResourceBuilder builder = new ResourceBuilder().setTreasury(resource.getTreasury());
        for (Map.Entry<Integer, Food> entry : resource.getFoodList().entrySet()) {
            builder.addFood(entry.getValue().getAmount(), entry.getKey());
        }
        return builder;
    }

    public ResourceBuilder addFood(int amount, int expirationYear) {
        resource.addFood(amount, expirationYear);
        return this;
    }

    public ResourceBuilder setTreasury(int treasury) {
        resource.setTreasury(treasury);
        return this;
    }

    public Resource build() {
        return resource;
    }

}
