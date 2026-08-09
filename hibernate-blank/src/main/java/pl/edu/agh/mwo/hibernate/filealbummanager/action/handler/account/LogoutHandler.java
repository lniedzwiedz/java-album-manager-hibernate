package pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.account.LogoutResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;

public class LogoutHandler {

    public MenuResult handle(LogoutResult result, String userName) {
        if (result == null)
            return MenuResult.CONTINUE;

        switch (result) {
            case LOGGED_OUT:
                System.out.println(String.format(AccountMessages.GOODBYE, userName));
                return MenuResult.EXIT;

            case LOGOUT_CANCELLED:
                System.out.println(AccountMessages.LOGOUT_CANCELLED);
                break;

            case INVALID_INPUT:
                System.out.println(ApplicationMessages.INVALID_INPUT_E3);
                break;

            default:
                break;
        }
        return MenuResult.CONTINUE;
    }
}