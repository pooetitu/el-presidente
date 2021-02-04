package state;

import game.Island;

public class GameState extends State {
    private Island island;
    private int turn;

    public GameState(int id) {
        super(id);
    }

    @Override
    public void init() {
        turn = 0;
    }

    public void initGame(Island island) {

    }

    @Override
    public void run() {

        turn++;
    }

}
