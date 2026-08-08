package pl.edu.agh.mwo.hibernate.filealbummanager.action.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.option.ConfirmationOption;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;

import java.io.IOException;

public class LogoutAction {

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {
        if (userLogged == null)
            return MenuResult.EXIT;

        System.out.println(AccountMessages.CONFIRM_LOGOUT);

        Integer input = reader.readInteger();
        if (input == null) {
            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
            return MenuResult.CONTINUE;
        }

        ConfirmationOption logoutOption = ConfirmationOption.fromInt(input);

        if (logoutOption == ConfirmationOption.YES) {
            System.out.println(String.format(AccountMessages.GOODBYE, userLogged.getName()));
            return MenuResult.EXIT;
        } else if (logoutOption == ConfirmationOption.NO) {
            System.out.println(AccountMessages.LOGOUT_CANCELLED);
        } else {
            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
        }
        return MenuResult.CONTINUE;
    }
}