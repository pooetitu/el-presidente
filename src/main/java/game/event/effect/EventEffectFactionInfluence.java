package game.event.effect;

import game.Island;

import java.util.ArrayList;

public class EventEffectFactionInfluence extends EventEffect {
    private ArrayList<String> factions;

    public EventEffectFactionInfluence(String description, double amount) {
        super(description, amount);
    }

    @Override
    public void applyEffect(Island island) {

    }

    public void addFaction(String name) {

    }
}
