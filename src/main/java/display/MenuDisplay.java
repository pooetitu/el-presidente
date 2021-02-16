package display;

import main.Main;

import java.io.IOException;
import java.net.URISyntaxException;

public abstract class MenuDisplay {
    private String choicesDisplay;
    private int switchSize;

    public MenuDisplay(String choicesDisplay, int switchSize) {
        this.choicesDisplay = choicesDisplay;
        this.switchSize = switchSize;
    }

    private void display() {
        System.out.println(choicesDisplay);
    }

    public boolean displayMenu() throws IOException, URISyntaxException {
        if (switchSize <= 0) return false;
        return execute(getChoice());
    }

    protected abstract boolean execute(int choice) throws IOException, URISyntaxException;

    protected int getChoice() {
        int choice = -1;
        while (choice < 0 || choice >= switchSize) {
            display();
            while (!Main.SCANNER.hasNextInt()) {
                display();
                Main.SCANNER.next();
            }
            choice = Main.SCANNER.nextInt();
        }
        return choice;
    }

    public int getSwitchSize() {
        return switchSize;
    }

    public void setChoicesDisplay(String choicesDisplay) {
        this.choicesDisplay = choicesDisplay;
    }

    public void setSwitchSize(int switchSize) {
        this.switchSize = switchSize;
    }
}
