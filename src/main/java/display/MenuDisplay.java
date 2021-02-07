package display;

import java.io.IOException;
import java.util.Scanner;

public abstract class MenuDisplay {
    private final String choicesDisplay;
    private final int switchSize;

    public MenuDisplay(String choicesDisplay, int switchSize) {
        this.choicesDisplay = choicesDisplay;
        this.switchSize = switchSize;
    }

    private void display() {
        System.out.println(choicesDisplay);
    }

    public boolean displayMenu(Scanner scanner) throws IOException {
        display();
        return execute(getChoice(scanner));
    }

    protected abstract boolean execute(int choice) throws IOException;

    private int getChoice(Scanner scanner) {
        scanner.hasNextInt();
        int choice = scanner.nextInt();
        while (choice < 0 || choice > switchSize) {
            display();
            scanner.hasNextInt();
            choice = scanner.nextInt();
        }
        return choice;
    }

}
