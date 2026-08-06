package pl.edu.agh.mwo.hibernate.filealbummanager.action.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.option.ConfirmationOption;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.option.LoginOption;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;

import java.io.IOException;

public class LoginAction {

    private final UserService userService;
    private final CreateAccountAction createAccountAction;

    public LoginAction(UserService userService, CreateAccountAction createAccountAction) {
        this.userService = userService;
        this.createAccountAction = createAccountAction;
    }

    public User execute(ConsoleReader reader) throws IOException {

        while (true) {
            System.out.println(AccountMessages.SELECT_LOGIN_OR_CREATE);

            String decision = reader.readLine();
            Integer decisionValue = parseInteger(decision);

            if (decisionValue == null) {
                continue;
            }

            LoginOption loginOption = LoginOption.fromInt(decisionValue);

            if (loginOption == null) {
                System.out.println(ApplicationMessages.INVALID_INPUT_E3);
                continue;
            }

            if (loginOption == LoginOption.LOGIN) {
                User userLogged = loginExistingUser(reader);
                if (userLogged != null) {
                    return userLogged;
                }

            } else if (loginOption == LoginOption.CREATE_ACCOUNT) {
                User userLogged = createAccountAction.execute(reader);
                if (userLogged != null) {
                    return userLogged;
                }
            }
        }
    }

    private User loginExistingUser(ConsoleReader reader) throws IOException {

        System.out.println(AccountMessages.LOGIN_USERNAME);

        String userName = reader.readLine();
        User userLogged = userService.getUserFromDatabase(userName);

        if (userLogged != null) {
            System.out.println(String.format(AccountMessages.WELCOME, userLogged.getName()));
            return userLogged;
        }

        System.out.println(AccountMessages.USER_NOT_FOUND_RETRY);
        System.out.println(AccountMessages.SELECT_CREATE_RETRY);

        String retry = reader.readLine();
        Integer retryValue = parseInteger(retry);

        if (retryValue == null) {
            return null;
        }

        ConfirmationOption retryOption = ConfirmationOption.fromInt(retryValue);

        if (retryOption == ConfirmationOption.YES) {
            userService.addUser(userName);
            userLogged = userService.getUserFromDatabase(userName);

            if (userLogged != null) {
                System.out.println(String.format(AccountMessages.WELCOME_ACCOUNT_CREATED, userLogged.getName()));
            }
            return userLogged;
        }

        if (retryOption == ConfirmationOption.NO) {
            return null;
        }

        System.out.println(ApplicationMessages.INVALID_INPUT_E3);
        return null;
    }

    private Integer parseInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
            return null;
        }
    }
}