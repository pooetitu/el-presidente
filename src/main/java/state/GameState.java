package state;

import game.Island;

public class GameState extends State {
    private Island island;
    private int turn;

    public GameState(int id) {
        super(id);
        turn = 0;
    }

    @Override
    public void run() {

        turn++;
    }

}
