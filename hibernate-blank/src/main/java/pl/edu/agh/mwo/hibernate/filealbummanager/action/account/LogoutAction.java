package pl.edu.agh.mwo.hibernate.filealbummanager.action.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.account.LogoutHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.account.LogoutResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.option.ConfirmationOption;

import java.io.IOException;

public class LogoutAction {

    private final LogoutHandler logoutHandler;

    public LogoutAction(LogoutHandler logoutHandler) {
        this.logoutHandler = logoutHandler;
    }

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {
        if (userLogged == null)
            return MenuResult.EXIT;

        System.out.println(AccountMessages.CONFIRM_LOGOUT);
        Integer input = reader.readInteger();

        if (input == null)
            return logoutHandler.handle(LogoutResult.INVALID_INPUT, userLogged.getName());

        ConfirmationOption option = ConfirmationOption.fromInt(input);
        if (option == ConfirmationOption.YES)
            return logoutHandler.handle(LogoutResult.LOGGED_OUT, userLogged.getName());

        if (option == ConfirmationOption.NO)
            return logoutHandler.handle(LogoutResult.LOGOUT_CANCELLED, userLogged.getName());

        return logoutHandler.handle(LogoutResult.INVALID_INPUT, userLogged.getName());
    }
}