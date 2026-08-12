package pl.edu.agh.mwo.hibernate.filealbummanager.handler.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.account.LoginResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;

public class LoginHandler {

    public void handle(LoginResult result) {
        if (result == null)
            return;

        switch (result.getStatus()) {
            case LOGGED_IN:
                System.out.println(String.format(AccountMessages.WELCOME, result.getUserName()));
                break;

            case USER_NOT_FOUND:
                System.out.println(AccountMessages.USER_NOT_FOUND_LOGIN);
                break;

            case INVALID_INPUT:
                System.out.println(ApplicationMessages.INVALID_INPUT_E3);
                break;
        }
    }
}