package pl.edu.agh.mwo.hibernate.filealbummanager.action.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.option.ConfirmationOption;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;

import java.io.BufferedReader;
import java.io.IOException;

public class LogoutAction {

    public boolean execute(ConsoleReader reader, User userLogged) throws IOException {
        if (userLogged == null) {
            return false;
        }

        System.out.println(AccountMessages.CONFIRM_LOGOUT);

        String logoutDecision = reader.readLine();
        int logoutValue;

        try {
            logoutValue = Integer.parseInt(logoutDecision);
        } catch (NumberFormatException e) {
            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
            return false;
        }

        ConfirmationOption logoutOption = ConfirmationOption.fromInt(logoutValue);

        if (logoutOption == ConfirmationOption.YES) {
            System.out.println(String.format(AccountMessages.GOODBYE, userLogged.getName()));
            return true;
        } else if (logoutOption == ConfirmationOption.NO) {
            System.out.println(AccountMessages.LOGOUT_CANCELLED);
        } else {
            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
        }
        return false;
    }
}