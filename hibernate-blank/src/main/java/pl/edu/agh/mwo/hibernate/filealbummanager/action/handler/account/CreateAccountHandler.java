package pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.account.AccountCreateResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;

public class CreateAccountHandler {

    public void handle(AccountCreateResult result) {
        if (result == null)
            return;

        switch (result.getStatus()) {
            case ACCOUNT_CREATED:
                System.out.println(String.format(AccountMessages.ACCOUNT_CREATED, result.getUserName()));
                break;

            case ACCOUNT_EXISTS:
                System.out.println(String.format(AccountMessages.ACCOUNT_EXISTS, result.getUserName()));
                break;

            case INVALID_INPUT:
                System.out.println(ApplicationMessages.INVALID_INPUT_E3);
                break;
        }
    }
}