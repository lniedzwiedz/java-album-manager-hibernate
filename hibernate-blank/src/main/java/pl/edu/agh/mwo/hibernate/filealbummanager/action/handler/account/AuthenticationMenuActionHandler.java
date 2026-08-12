package pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.account.CreateAccountAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.account.LoginAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.account.AccountCreateResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.account.AccountCreateStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.account.AuthenticationResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.account.AuthenticationStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.account.LoginResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.account.LoginStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.option.LoginOption;

import java.io.IOException;

public class AuthenticationMenuActionHandler {

    private final LoginAction loginAction;
    private final CreateAccountAction createAccountAction;

    public AuthenticationMenuActionHandler(LoginAction loginAction, CreateAccountAction createAccountAction) {
        this.loginAction = loginAction;
        this.createAccountAction = createAccountAction;
    }

    public AuthenticationResult execute(ConsoleReader reader, LoginOption loginOption) throws IOException {
        if (loginOption == null)
            return new AuthenticationResult(AuthenticationStatus.INVALID_INPUT, null);

        switch (loginOption) {
            case LOGIN:
                LoginResult loginResult = loginAction.execute(reader);

                if (loginResult.getStatus() == LoginStatus.LOGGED_IN)
                    return new AuthenticationResult(AuthenticationStatus.LOGGED_IN, loginResult.getUserName());

                return new AuthenticationResult(AuthenticationStatus.INVALID_INPUT, null);

            case CREATE_ACCOUNT:
                AccountCreateResult createResult = createAccountAction.execute(reader);

                if (createResult.getStatus() == AccountCreateStatus.ACCOUNT_CREATED)
                    return new AuthenticationResult(AuthenticationStatus.LOGGED_IN, createResult.getUserName());

                return new AuthenticationResult(AuthenticationStatus.INVALID_INPUT, null);

            case EXIT:
                return new AuthenticationResult(AuthenticationStatus.EXIT, null);

            default:
                return new AuthenticationResult(AuthenticationStatus.INVALID_INPUT, null);
        }
    }
}