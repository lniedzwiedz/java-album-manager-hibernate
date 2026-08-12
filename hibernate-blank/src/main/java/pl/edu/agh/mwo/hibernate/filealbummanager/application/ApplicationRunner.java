package pl.edu.agh.mwo.hibernate.filealbummanager.application;

import pl.edu.agh.mwo.hibernate.filealbummanager.handler.account.AuthenticationMenuActionHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.handler.MenuActionHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.menu.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.account.AuthenticationResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.status.account.AuthenticationStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleMenu;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsolePrinter;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.option.LoginOption;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.option.MenuOption;

import java.io.IOException;

public class ApplicationRunner {

    private final AuthenticationMenuActionHandler authenticationMenuActionHandler;
    private final MenuActionHandler menuActionHandler;
    private final UserService userService;
    private final ConsoleReader consoleReader;
    private final ConsolePrinter consolePrinter;
    private final ConsoleMenu consoleMenu;

    public ApplicationRunner(AuthenticationMenuActionHandler authenticationMenuActionHandler, MenuActionHandler menuActionHandler, UserService userService, ConsoleReader consoleReader, ConsolePrinter consolePrinter, ConsoleMenu consoleMenu) {

        this.authenticationMenuActionHandler = authenticationMenuActionHandler;
        this.menuActionHandler = menuActionHandler;
        this.userService = userService;
        this.consoleReader = consoleReader;
        this.consolePrinter = consolePrinter;
        this.consoleMenu = consoleMenu;
    }

    public void run() throws IOException {

        boolean applicationRunning = true;

        while (applicationRunning) {

            consolePrinter.printApplicationTitle();

            AuthenticationResult authenticationResult = login();

            if (authenticationResult.getStatus() == AuthenticationStatus.EXIT) {
                applicationRunning = false;
                continue;
            }

            if (authenticationResult.getStatus() == AuthenticationStatus.LOGGED_IN) {
                User userLogged = userService.getUser(authenticationResult.getUserName());
                runMenu(userLogged);
            }
        }
    }

    private void runMenu(User userLogged) throws IOException {

        boolean menuRunning = true;

        while (menuRunning && userLogged != null) {

            MenuOption menuOption = consoleMenu.readMenuOption();

            if (menuOption == null)
                continue;

            MenuResult menuResult = menuActionHandler.execute(menuOption, consoleReader, userLogged);
            if (menuResult == MenuResult.EXIT) {
                menuRunning = false;
            }
        }
    }

    private AuthenticationResult login() throws IOException {

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

            AuthenticationResult result = authenticationMenuActionHandler.execute(consoleReader, loginOption);
            if (result.getStatus() == AuthenticationStatus.LOGGED_IN)
                return result;

            if (result.getStatus() == AuthenticationStatus.EXIT)
                return result;
        }
    }
}