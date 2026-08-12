package pl.edu.agh.mwo.hibernate.filealbummanager.action.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.handler.account.LoginHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.account.LoginResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.status.account.LoginStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;

import java.io.IOException;

public class LoginAction {

    private final UserService userService;
    private final LoginHandler loginHandler;

    public LoginAction(UserService userService, LoginHandler loginHandler) {
        this.userService = userService;
        this.loginHandler = loginHandler;
    }

    public LoginResult execute(ConsoleReader reader) throws IOException {

        System.out.println(AccountMessages.LOGIN_USERNAME);
        String userName = reader.readLine();
        LoginResult result;

        if (userName == null || userName.isBlank()) {
            result = new LoginResult(LoginStatus.INVALID_INPUT, null);

        } else if (userService.getUser(userName) == null) {
            result = new LoginResult(LoginStatus.USER_NOT_FOUND, userName);

        } else {
            result = new LoginResult(LoginStatus.LOGGED_IN, userName);
        }
        loginHandler.handle(result);
        return result;
    }
}