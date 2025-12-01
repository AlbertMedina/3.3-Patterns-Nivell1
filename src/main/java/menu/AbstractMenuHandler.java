package menu;

import input.InputHandler;

public abstract class AbstractMenuHandler {

    public void run() {
        int option;
        do {
            showMenuOptions();
            option = InputHandler.readInt("Choose what to do next");
            System.out.println();
            handleOption(option);
            System.out.println();
        } while (option != 0);
    }

    protected abstract void showMenuOptions();

    protected abstract void handleOption(int option);
}
