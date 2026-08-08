package pl.edu.agh.mwo.hibernate.filealbummanager.action;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.account.CreateAccountAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.account.LoginAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.option.LoginOption;

import java.io.IOException;

public class LoginActionHandler {

    private final LoginAction loginAction;
    private final CreateAccountAction createAccountAction;

    public LoginActionHandler(LoginAction loginAction, CreateAccountAction createAccountAction) {
        this.loginAction = loginAction;
        this.createAccountAction = createAccountAction;
    }

    public User execute(LoginOption loginOption, ConsoleReader reader) throws IOException {

        switch (loginOption) {

            case LOGIN:
                return loginAction.execute(reader);

            case CREATE_ACCOUNT:
                return createAccountAction.execute(reader);

            default:
                return null;
        }
    }
}