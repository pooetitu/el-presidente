package display;

import game.GameDifficulty;

public class DifficultyMenuDisplay extends MenuDisplay {

    private GameDifficulty gameDifficulty;

    public DifficultyMenuDisplay(String choicesDisplay) {
        super(choicesDisplay, 3);
    }

    public GameDifficulty getGameDifficulty() {
        return gameDifficulty;
    }

    @Override
    protected boolean execute(int choice) {
        switch (choice) {
            case 0: {
                gameDifficulty = GameDifficulty.EASY;
                break;
            }
            case 1: {
                gameDifficulty = GameDifficulty.NORMAL;
                break;
            }
            case 2: {
                gameDifficulty = GameDifficulty.HARD;
                break;
            }
        }
        return true;
    }
}
