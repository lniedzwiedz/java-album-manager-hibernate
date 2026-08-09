package pl.edu.agh.mwo.hibernate.filealbummanager.action.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.account.DeleteAccountHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.account.AccountDeleteResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.option.ConfirmationOption;

import java.io.IOException;

public class DeleteAccountAction {

    private final UserService userService;
    private final DeleteAccountHandler deleteAccountHandler;

    public DeleteAccountAction(UserService userService, DeleteAccountHandler deleteAccountHandler) {
        this.userService = userService;
        this.deleteAccountHandler = deleteAccountHandler;
    }

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {
        if (userLogged == null)
            return MenuResult.CONTINUE;

        System.out.println(AccountMessages.CONFIRM_DELETE_ACCOUNT);
        Integer input = reader.readInteger();

        if (input == null)
            return deleteAccountHandler.handle(AccountDeleteResult.INVALID_INPUT, userLogged.getName());

        ConfirmationOption option = ConfirmationOption.fromInt(input);
        if (option == ConfirmationOption.YES) {
            String deletedUserName = userLogged.getName();
            userService.delete(userLogged);
            return deleteAccountHandler.handle(AccountDeleteResult.ACCOUNT_DELETED, deletedUserName);
        }

        if (option == ConfirmationOption.NO)
            return deleteAccountHandler.handle(AccountDeleteResult.ACCOUNT_NOT_DELETED, userLogged.getName());

        return deleteAccountHandler.handle(AccountDeleteResult.INVALID_INPUT, userLogged.getName());
    }
}