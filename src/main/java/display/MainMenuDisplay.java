package display;

public class MainMenuDisplay extends MenuDisplay {
    public MainMenuDisplay(String choicesDisplay) {
        super(choicesDisplay, 3);
    }

    @Override
    protected boolean execute(int choice) {
        switch (choice) {
            case 0: {
                break;
            }
            case 1: {
                break;
            }
            case 2: {
                System.exit(0);
            }
        }
        return true;
    }
}
