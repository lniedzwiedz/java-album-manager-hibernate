package pl.edu.agh.mwo.hibernate.filealbummanager.action.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.handler.account.CreateAccountHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.account.AccountCreateResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.status.account.AccountCreateStatus;
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

    public AccountCreateResult execute(ConsoleReader reader) throws IOException {

        System.out.println(AccountMessages.CREATE_ACCOUNT_USERNAME);
        String userName = reader.readLine();
        AccountCreateResult result;

        if (userName == null || userName.isBlank()) {
            result = new AccountCreateResult(AccountCreateStatus.INVALID_INPUT, null);

        } else if (userService.getUser(userName) != null) {
            result = new AccountCreateResult(AccountCreateStatus.ACCOUNT_EXISTS, userName);

        } else {
            userService.createUser(userName);
            result = new AccountCreateResult(AccountCreateStatus.ACCOUNT_CREATED, userName);
        }
        createAccountHandler.handle(result);
        return result;
    }
}