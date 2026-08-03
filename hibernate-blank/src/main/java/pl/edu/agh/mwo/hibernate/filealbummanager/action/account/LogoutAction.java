package pl.edu.agh.mwo.hibernate.filealbummanager.action.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.BinaryOption;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.Messages;

import java.io.BufferedReader;
import java.io.IOException;

public class LogoutAction {

    public boolean execute(BufferedReader br, User userLogged) throws IOException {
        System.out.println(Messages.CONFIRM_LOGOUT);
        String logoutDecision = br.readLine();
        int logoutValue;
        try {
            logoutValue = Integer.parseInt(logoutDecision);
        } catch (NumberFormatException e) {
            System.out.println(Messages.INVALID_INPUT_E3);
            return false;
        }
        BinaryOption logoutOption = BinaryOption.fromInt(logoutValue);
        if (logoutOption == BinaryOption.YES) {
            System.out.println(String.format(Messages.GOODBYE, userLogged.getName()));
            return true;
        } else if (logoutOption == BinaryOption.NO) {
            System.out.println();
        } else {
            System.out.println(Messages.INVALID_INPUT_E3);
        }
        return false;
    }
}