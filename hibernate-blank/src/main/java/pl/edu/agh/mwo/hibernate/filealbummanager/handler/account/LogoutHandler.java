package pl.edu.agh.mwo.hibernate.filealbummanager.handler.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.account.LogoutResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;

public class LogoutHandler {

    public void handle(LogoutResult result) {
        if (result == null)
            return;

        switch (result.getStatus()) {
            case LOGGED_OUT:
                System.out.println(String.format(AccountMessages.GOODBYE, result.getUserName()));
                break;

            case LOGOUT_CANCELLED:
                System.out.println(AccountMessages.LOGOUT_CANCELLED);
                break;

            case INVALID_INPUT:
                System.out.println(ApplicationMessages.INVALID_INPUT_E3);
                break;
        }
    }
}