package com.presidente.builders;

import com.presidente.game.Food;
import com.presidente.game.Resource;

import java.util.Map;

public class ResourceBuilder {
    private final Resource resource;

    public ResourceBuilder() {
        this.resource = new Resource();
    }

    /**
     * Clone the data of the given resource instance to a newly created ResourceBuilder instance by setting the data to the new builder
     *
     * @param resource The Resource instance to be cloned
     * @return An instance of the Builder of the cloned resource
     */
    public static ResourceBuilder clone(Resource resource) {
        ResourceBuilder builder = new ResourceBuilder().setTreasury(resource.getTreasury());
        for (Map.Entry<Integer, Food> entry : resource.getFoodList().entrySet()) {
            builder.addFood(entry.getValue().getAmount(), entry.getKey());
        }
        return builder;
    }

    /**
     * Add a new Food with its year of expiration to the Resource
     *
     * @param amount         The amount of food to be added at the given expiration date
     * @param expirationYear The year at which the food will be expired
     * @return The instance of the current builder
     */
    public ResourceBuilder addFood(int amount, int expirationYear) {
        resource.addFood(amount, expirationYear);
        return this;
    }

    /**
     * Set the amount money available to the Resource
     *
     * @param treasury The amount of money to be set to the Resource
     * @return The instance of the current builder
     */
    public ResourceBuilder setTreasury(int treasury) {
        resource.setTreasury(treasury);
        return this;
    }

    /**
     * @return Return the built instance of Resource
     */
    public Resource build() {
        return resource;
    }
}
