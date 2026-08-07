package pl.edu.agh.mwo.hibernate.filealbummanager.application;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.MenuActionHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.account.LoginAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleMenu;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsolePrinter;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.option.MenuOption;

import java.awt.*;
import java.io.IOException;

public class ApplicationRunner {

    private final MenuActionHandler menuActionHandler;
    private final LoginAction loginAction;
    private final ConsolePrinter consolePrinter;
    private final ConsoleReader consoleReader;
    private final ConsoleMenu consoleMenu;

    public ApplicationRunner(
            MenuActionHandler menuActionHandler,
            LoginAction loginAction,
            ConsolePrinter consolePrinter,
            ConsoleReader consoleReader,
            ConsoleMenu consoleMenu) {

        this.menuActionHandler = menuActionHandler;
        this.loginAction = loginAction;
        this.consolePrinter = consolePrinter;
        this.consoleReader = consoleReader;
        this.consoleMenu = consoleMenu;
    }

    public void run() throws IOException {
        while (true) {
            consolePrinter.printApplicationTitle();

            User userLogged = loginAction.execute(consoleReader);
            if (userLogged == null)
                continue;

            runMenu(userLogged);
        }
    }

    private void runMenu(User userLogged) throws IOException {
        boolean menuRunning = true;

        while (menuRunning && userLogged != null) {
            MenuOption menuOption = consoleMenu.readMenuOption();

            if (menuOption == null)
                continue;

            MenuResult menuResult =
                    menuActionHandler.execute(
                            menuOption,
                            consoleReader,
                            userLogged
                    );

            if (menuResult == MenuResult.EXIT)
                menuRunning = false;
        }
    }
}