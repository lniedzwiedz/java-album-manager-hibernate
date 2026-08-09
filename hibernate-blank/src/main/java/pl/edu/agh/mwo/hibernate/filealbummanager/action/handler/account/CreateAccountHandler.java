package pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.account.AccountCreateResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;

public class CreateAccountHandler {

    public MenuResult handle(AccountCreateResult result, User user) {
        if (result == null)
            return MenuResult.CONTINUE;

        switch (result) {
            case ACCOUNT_CREATED:
                System.out.println(String.format(AccountMessages.WELCOME_ACCOUNT_CREATED, user.getName()));
                break;

            case ACCOUNT_EXISTS:
                System.out.println(String.format(AccountMessages.WELCOME, user.getName()));
                System.out.println(AccountMessages.ACCOUNT_EXISTS_AUTO_LOGIN);
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