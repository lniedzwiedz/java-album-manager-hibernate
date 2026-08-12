package pl.edu.agh.mwo.hibernate.filealbummanager.action.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.handler.account.LogoutHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.account.LogoutResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.status.account.LogoutStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.option.ConfirmationOption;

import java.io.IOException;

public class LogoutAction {

    private final LogoutHandler logoutHandler;

    public LogoutAction(LogoutHandler logoutHandler) {
        this.logoutHandler = logoutHandler;
    }

    public LogoutResult execute(ConsoleReader reader, User userLogged) throws IOException {

        LogoutResult result;

        if (userLogged == null || userLogged.getId() <= 0) {
            result = new LogoutResult(LogoutStatus.INVALID_INPUT, null);

        } else {
            System.out.println(AccountMessages.CONFIRM_LOGOUT);
            Integer input = reader.readInteger();

            if (input == null) {
                result = new LogoutResult(LogoutStatus.INVALID_INPUT, userLogged.getName());

            } else {
                ConfirmationOption option = ConfirmationOption.fromInt(input);

                if (option == ConfirmationOption.YES) {
                    result = new LogoutResult(LogoutStatus.LOGGED_OUT, userLogged.getName());

                } else if (option == ConfirmationOption.NO) {
                    result = new LogoutResult(LogoutStatus.LOGOUT_CANCELLED, userLogged.getName());

                } else {
                    result = new LogoutResult(LogoutStatus.INVALID_INPUT, userLogged.getName());
                }
            }
        }
        logoutHandler.handle(result);
        return result;
    }
}