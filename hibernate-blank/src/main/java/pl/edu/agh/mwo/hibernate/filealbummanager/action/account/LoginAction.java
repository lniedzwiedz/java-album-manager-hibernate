package pl.edu.agh.mwo.hibernate.filealbummanager.action.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;

import java.io.IOException;

public class LoginAction {

    private final UserService userService;
    private final CreateAccountAction createAccountAction;

    public LoginAction(UserService userService, CreateAccountAction createAccountAction) {
        this.userService = userService;
        this.createAccountAction = createAccountAction;
    }

    public User execute(ConsoleReader reader) throws IOException {
        System.out.println(AccountMessages.LOGIN_USERNAME);
        String userName = reader.readLine();
        if (userName == null || userName.isBlank()) {
            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
            return null;
        }

        User userLogged = userService.getUser(userName);
        if (userLogged == null) {
            System.out.println(AccountMessages.USER_NOT_FOUND_LOGIN);
            return null;
        }

        System.out.println(String.format(AccountMessages.WELCOME, userLogged.getName()));
        return userLogged;
    }
}