package pl.edu.agh.mwo.hibernate.filealbummanager.application;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.MenuActionHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.account.LoginAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.application.ApplicationMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.application.MenuOption;

import java.io.BufferedReader;
import java.io.IOException;

public class ApplicationRunner {

    private final MenuActionHandler menuActionHandler;
    private final LoginAction loginAction;

    public ApplicationRunner(MenuActionHandler menuActionHandler, LoginAction loginAction) {
        this.menuActionHandler = menuActionHandler;
        this.loginAction = loginAction;
    }

    public void run(BufferedReader br) throws IOException {
        boolean running = true;

        while (running) {

            printApplicationTitle();
            User userLogged = loginAction.execute(br);
            if (userLogged == null)
                continue;

            runMenu(br, userLogged);
        }
    }

    private void runMenu(BufferedReader br, User userLogged) throws IOException {
        boolean menuRunning = true;

        while (menuRunning && userLogged != null) {
            printMenu();
            String input = br.readLine();
            Integer inputValue = parseInteger(input);

            if (inputValue == null)
                continue;

            MenuOption inputOption = MenuOption.fromInt(inputValue);
            if (inputOption == null) {
                System.out.println(ApplicationMessages.INVALID_INPUT_E3);
                continue;
            }

            boolean shouldExit = menuActionHandler.execute(inputOption, br, userLogged);
            if (shouldExit)
                menuRunning = false;

        }
    }

    private Integer parseInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
            return null;
        }
    }

    private void printApplicationTitle() {
        System.out.println();
        System.out.println(ApplicationMessages.ALBUM_MANAGER_TITLE);
    }

    private void printMenu() {
        System.out.println();
        System.out.println(ApplicationMessages.MENU_HEADER);
        System.out.println(ApplicationMessages.MENU_OPTIONS);
    }
}