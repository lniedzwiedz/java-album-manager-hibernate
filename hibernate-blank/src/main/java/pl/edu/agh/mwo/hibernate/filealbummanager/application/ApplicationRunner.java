package pl.edu.agh.mwo.hibernate.filealbummanager.application;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.MenuActionHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.BinaryOption;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.LoginOption;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.MenuOption;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.Messages;

import java.io.BufferedReader;
import java.io.IOException;

public class ApplicationRunner {

    private final UserManagerService userManager;
    private final MenuActionHandler menuActionHandler;

    public ApplicationRunner(
            UserManagerService userManager,
            MenuActionHandler menuActionHandler
    ) {
        this.userManager = userManager;
        this.menuActionHandler = menuActionHandler;
    }

    public void run(
            BufferedReader br
    ) throws IOException {

        boolean running = true;

        while (running) {

            printApplicationTitle();

            User userLogged =
                    login(br);

            if (userLogged == null) {
                continue;
            }

            boolean logout =
                    runMenu(
                            br,
                            userLogged
                    );

            if (logout) {
                running = true;
            }
        }
    }

    private User login(
            BufferedReader br
    ) throws IOException {

        boolean loggedIn = false;

        User userLogged = null;

        while (!loggedIn) {

            System.out.println(
                    Messages.SELECT_LOGIN_OR_CREATE
            );

            String decision =
                    br.readLine();

            Integer decisionValue =
                    parseInteger(decision);

            if (decisionValue == null) {
                continue;
            }

            LoginOption loginOption =
                    LoginOption.fromInt(
                            decisionValue
                    );

            if (loginOption == null) {

                System.out.println(
                        Messages.INVALID_INPUT_E3
                );

                continue;
            }

            if (loginOption == LoginOption.LOGIN) {

                userLogged =
                        loginExistingUser(br);

                if (userLogged != null) {
                    loggedIn = true;
                }

            } else if (
                    loginOption == LoginOption.CREATE_ACCOUNT
            ) {

                userLogged =
                        createAccount(br);

                loggedIn = true;
            }
        }

        return userLogged;
    }

    private User loginExistingUser(
            BufferedReader br
    ) throws IOException {

        System.out.println(
                Messages.LOGIN_USERNAME
        );

        String userName =
                br.readLine();

        User userLogged =
                userManager.getUserFromDatabase(
                        userName
                );

        if (userLogged != null) {

            System.out.println(
                    String.format(
                            Messages.WELCOME,
                            userLogged.getName()
                    )
            );

            return userLogged;
        }

        System.out.println(
                Messages.USER_NOT_FOUND_RETRY
        );

        System.out.println(
                Messages.SELECT_CREATE_RETRY
        );

        String retry =
                br.readLine();

        Integer retryValue =
                parseInteger(retry);

        if (retryValue == null) {
            return null;
        }

        BinaryOption retryOption =
                BinaryOption.fromInt(
                        retryValue
                );

        if (retryOption == BinaryOption.YES) {

            userManager.addUser(
                    userName
            );

            userLogged =
                    userManager.getUserFromDatabase(
                            userName
                    );

            System.out.println(
                    String.format(
                            Messages.WELCOME_ACCOUNT_CREATED,
                            userLogged.getName()
                    )
            );

            return userLogged;
        }

        return null;
    }

    private User createAccount(
            BufferedReader br
    ) throws IOException {

        System.out.println(
                Messages.CREATE_ACCOUNT_USERNAME
        );

        String userName =
                br.readLine();

        User userLogged =
                userManager.getUserFromDatabase(
                        userName
                );

        if (userLogged != null) {

            System.out.println(
                    String.format(
                            Messages.WELCOME,
                            userLogged.getName()
                    )
            );

            System.out.println(
                    Messages.ACCOUNT_EXISTS_AUTO_LOGIN
            );

            return userLogged;
        }

        userManager.addUser(
                userName
        );

        userLogged =
                userManager.getUserFromDatabase(
                        userName
                );

        System.out.println(
                String.format(
                        Messages.WELCOME_ACCOUNT_CREATED_EXCLAMATION,
                        userLogged.getName()
                )
        );

        return userLogged;
    }

    private boolean runMenu(
            BufferedReader br,
            User userLogged
    ) throws IOException {

        boolean menuRunning = true;

        while (
                menuRunning &&
                        userLogged != null
        ) {

            printMenu();

            String input =
                    br.readLine();

            Integer inputValue =
                    parseInteger(input);

            if (inputValue == null) {
                continue;
            }

            MenuOption inputOption =
                    MenuOption.fromInt(
                            inputValue
                    );

            if (inputOption == null) {

                System.out.println(
                        Messages.INVALID_INPUT_E3
                );

                continue;
            }

            boolean shouldExit =
                    menuActionHandler.execute(
                            inputOption,
                            br,
                            userLogged
                    );

            if (shouldExit) {
                menuRunning = false;
            }
        }

        return true;
    }

    private Integer parseInteger(
            String input
    ) {

        try {

            return Integer.parseInt(
                    input
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    Messages.INVALID_INPUT_E3
            );

            return null;
        }
    }

    private void printApplicationTitle() {

        System.out.println();

        System.out.println(
                Messages.ALBUM_MANAGER_TITLE
        );
    }

    private void printMenu() {

        System.out.println();

        System.out.println(
                Messages.MENU_HEADER
        );

        System.out.println(
                Messages.MENU_OPTIONS
        );
    }
}

