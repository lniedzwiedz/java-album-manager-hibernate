package pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.account.AccountDeleteResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;

public class DeleteAccountHandler {

    public MenuResult handle(AccountDeleteResult result, String userName) {
        if (result == null)
            return MenuResult.CONTINUE;

        switch (result) {
            case ACCOUNT_DELETED:
                System.out.println(String.format(AccountMessages.GOODBYE, userName));
                System.out.println(AccountMessages.ACCOUNT_DELETED);
                return MenuResult.EXIT;

            case ACCOUNT_NOT_DELETED:
                System.out.println(AccountMessages.ACCOUNT_NOT_DELETED);
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