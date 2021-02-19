package com.presidente.display;

import com.presidente.main.Main;

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

    public boolean displayMenu() {
        if (switchSize <= 0) return false;
        return execute(getChoice());
    }

    protected abstract boolean execute(int choice);

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

    public void setSwitchSize(int switchSize) {
        this.switchSize = switchSize;
    }

    public void setChoicesDisplay(String choicesDisplay) {
        this.choicesDisplay = choicesDisplay;
    }
}
