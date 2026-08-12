package pl.edu.agh.mwo.hibernate.filealbummanager.handler.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.account.AccountDeleteResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;

public class DeleteAccountHandler {

    public void handle(AccountDeleteResult result) {
        if (result == null)
            return;

        switch (result.getStatus()) {
            case ACCOUNT_DELETED:
                System.out.println(String.format(AccountMessages.GOODBYE, result.getUserName()));
                System.out.println(AccountMessages.ACCOUNT_DELETED);
                break;

            case ACCOUNT_NOT_DELETED:
                System.out.println(AccountMessages.ACCOUNT_NOT_DELETED);
                break;

            case INVALID_INPUT:
                System.out.println(ApplicationMessages.INVALID_INPUT_E3);
                break;
        }
    }
}