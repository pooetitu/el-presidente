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
        this.island = island;
        System.out.println("init game state");
    }

    @Override
    public void run() {
        System.out.println(turn);
        turn++;
    }

}
