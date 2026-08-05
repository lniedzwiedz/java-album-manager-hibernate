package pl.edu.agh.mwo.hibernate.filealbummanager.action.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.ConfirmationOption;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.application.ApplicationMessages;

import java.io.BufferedReader;
import java.io.IOException;

public class LogoutAction {

    public boolean execute(BufferedReader br, User userLogged) throws IOException {
        if (userLogged == null) {
            return false;
        }

        System.out.println(AccountMessages.CONFIRM_LOGOUT);

        String logoutDecision = br.readLine();
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
            System.out.println();
        } else {
            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
        }
        return false;
    }
}