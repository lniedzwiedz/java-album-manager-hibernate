package pl.edu.agh.mwo.hibernate.filealbummanager.action.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;

import java.io.IOException;

public class CreateAccountAction {

    private final UserService userService;

    public CreateAccountAction(UserService userService) {
        this.userService = userService;
    }

    public User execute(ConsoleReader reader) throws IOException {

        while (true) {

            System.out.println(AccountMessages.CREATE_ACCOUNT_USERNAME);

            String userName = reader.readLine();
            if (userName == null || userName.isBlank()) {
                System.out.println(ApplicationMessages.INVALID_INPUT_E3);
                continue;
            }

            User userLogged = userService.getUserFromDatabase(userName);
            if (userLogged != null) {
                System.out.println(String.format(AccountMessages.WELCOME, userLogged.getName()));
                System.out.println(AccountMessages.ACCOUNT_EXISTS_AUTO_LOGIN);
                return userLogged;
            }

            userService.addUser(userName);

            userLogged = userService.getUserFromDatabase(userName);
            if (userLogged != null)
                System.out.println(String.format(AccountMessages.WELCOME_ACCOUNT_CREATED, userLogged.getName()));
            return userLogged;
        }
    }
}