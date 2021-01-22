package state;

public abstract class State {
    private int id;

    public abstract void update();

    public abstract void display();

}
