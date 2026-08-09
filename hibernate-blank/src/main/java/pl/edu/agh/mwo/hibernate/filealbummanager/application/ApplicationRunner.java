package pl.edu.agh.mwo.hibernate.filealbummanager.application;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.account.LoginActionHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.MenuActionHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleMenu;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsolePrinter;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.option.LoginOption;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.option.MenuOption;

import java.io.IOException;

public class ApplicationRunner {

    private final MenuActionHandler menuActionHandler;
    private final LoginActionHandler loginActionHandler;
    private final ConsolePrinter consolePrinter;
    private final ConsoleReader consoleReader;
    private final ConsoleMenu consoleMenu;

    public ApplicationRunner(MenuActionHandler menuActionHandler, LoginActionHandler loginActionHandler, ConsolePrinter consolePrinter, ConsoleReader consoleReader, ConsoleMenu consoleMenu) {
        this.menuActionHandler = menuActionHandler;
        this.loginActionHandler = loginActionHandler;
        this.consolePrinter = consolePrinter;
        this.consoleReader = consoleReader;
        this.consoleMenu = consoleMenu;
    }

    public void run() throws IOException {
        while (true) {
            consolePrinter.printApplicationTitle();

            User userLogged = login();
            if (userLogged == null) continue;

            runMenu(userLogged);
        }
    }

    private void runMenu(User userLogged) throws IOException {
        boolean menuRunning = true;

        while (menuRunning && userLogged != null) {
            MenuOption menuOption = consoleMenu.readMenuOption();

            if (menuOption == null) continue;

            MenuResult menuResult = menuActionHandler.execute(menuOption, consoleReader, userLogged);
            if (menuResult == MenuResult.EXIT) menuRunning = false;
        }
    }

    private User login() throws IOException {
        while (true) {
            System.out.println(AccountMessages.SELECT_LOGIN_OR_CREATE);
            Integer input = consoleReader.readInteger();
            if (input == null) {
                System.out.println(ApplicationMessages.INVALID_INPUT_E3);
                continue;
            }

            LoginOption loginOption = LoginOption.fromInt(input);
            if (loginOption == null) {
                System.out.println(ApplicationMessages.INVALID_INPUT_E3);
                continue;
            }

            User userLogged = loginActionHandler.execute(loginOption, consoleReader);
            if (userLogged != null) {
                return userLogged;
            }
        }
    }
}