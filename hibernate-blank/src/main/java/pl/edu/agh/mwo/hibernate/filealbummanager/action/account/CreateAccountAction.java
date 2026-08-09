package pl.edu.agh.mwo.hibernate.filealbummanager.action.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.account.CreateAccountHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.account.AccountCreateResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;

import java.io.IOException;

public class CreateAccountAction {

    private final UserService userService;
    private final CreateAccountHandler createAccountHandler;

    public CreateAccountAction(UserService userService, CreateAccountHandler createAccountHandler) {
        this.userService = userService;
        this.createAccountHandler = createAccountHandler;
    }

    public User execute(ConsoleReader reader) throws IOException {
        while (true) {
            System.out.println(AccountMessages.CREATE_ACCOUNT_USERNAME);
            String userName = reader.readLine();

            if (userName == null || userName.isBlank()) {
                createAccountHandler.handle(AccountCreateResult.INVALID_INPUT, null);
                continue;
            }

            User user = userService.getUser(userName);
            if (user != null) {
                createAccountHandler.handle(AccountCreateResult.ACCOUNT_EXISTS, user);
                return user;
            }
            userService.createUser(userName);
            user = userService.getUser(userName);
            createAccountHandler.handle(AccountCreateResult.ACCOUNT_CREATED, user);
            return user;
        }
    }
}