package state;

import java.util.HashMap;

public abstract class State {
    public static final int MENU_STATE_ID = 0;
    public static final int GAME_STATE_ID = 1;
    private static final HashMap<Integer, State> states = new HashMap<>();
    private static int activeStateId = 0;
    private final int id;

    public State(int id) {
        this.id = id;
    }

    public static void addState(State state) {
        states.put(state.getId(), state);
    }

    public static State getStateById(int id) {
        return states.get(id);
    }

    public static State getActiveState() {
        return states.get(activeStateId);
    }

    public static void setActiveStateId(int activeStateId) {
        State.activeStateId = activeStateId;
    }

    public abstract void run();

    public int getId() {
        return id;
    }
}
