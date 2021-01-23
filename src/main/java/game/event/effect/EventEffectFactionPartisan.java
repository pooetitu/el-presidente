package game.event.effect;

import game.Island;

import java.util.ArrayList;

public class EventEffectFactionPartisan extends EventEffect {
    private ArrayList<String> factions;

    public EventEffectFactionPartisan(String description, double amount) {
        super(description, amount);
    }

    @Override
    public void applyEffect(Island island) {

    }
    public void addFaction(String name){

    }
}
