package pl.edu.agh.mwo.hibernate.filealbummanager.action.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserService;
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

//    public User execute(ConsoleReader reader) throws IOException {
//
//        while (true) {
//            System.out.println(AccountMessages.SELECT_LOGIN_OR_CREATE);
//
//            Integer decisionValue = reader.readInteger();
//            if (decisionValue == null) {
//                System.out.println(ApplicationMessages.INVALID_INPUT_E3);
//                continue;
//            }
//
//            LoginOption loginOption = LoginOption.fromInt(decisionValue);
//            if (loginOption == null) {
//                System.out.println(ApplicationMessages.INVALID_INPUT_E3);
//                continue;
//            }
//
//            if (loginOption == LoginOption.LOGIN) {
//                User userLogged = loginExistingUser(reader);
//                if (userLogged != null) {
//                    return userLogged;
//                }
//
//            } else if (loginOption == LoginOption.CREATE_ACCOUNT) {
//                User userLogged = createAccountAction.execute(reader);
//                if (userLogged != null) {
//                    return userLogged;
//                }
//            }
//        }
//    }

//    private User loginExistingUser(ConsoleReader reader) throws IOException {
public User execute(ConsoleReader reader) throws IOException {

        while (true) {
            System.out.println(AccountMessages.LOGIN_USERNAME);

            String userName = reader.readLine();
            if (userName == null || userName.isBlank()) {
                System.out.println(ApplicationMessages.INVALID_INPUT_E3);
                continue;
            }

            User userLogged = userService.getUserFromDatabase(userName);
            if (userLogged != null) {
                System.out.println(String.format(AccountMessages.WELCOME, userLogged.getName()));
                return userLogged;
            }

            System.out.println(AccountMessages.USER_NOT_FOUND_LOGIN_OPTIONS);

            Integer input = reader.readInteger();
            if (input == null) {
                System.out.println(ApplicationMessages.INVALID_INPUT_E3);
                continue;
            }

            LoginOption loginOption = LoginOption.fromInt(input);
            if (loginOption == null) {
                System.out.println(ApplicationMessages.INVALID_INPUT_E3);
                continue;
            }
//
//            if (loginOption == LoginOption.CREATE_ACCOUNT) {
//                return createAccountAction.execute(reader);
//            } else if (loginOption == LoginOption.TRY_AGAIN) {
//                continue;
//            }

            if (loginOption == LoginOption.TRY_AGAIN) {
                continue;
            }
        }
    }
}