package pl.edu.agh.mwo.hibernate.filealbummanager.action.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.account.DeleteAccountHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.account.AccountDeleteResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.account.AccountDeleteStatus;
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

    public AccountDeleteResult execute(ConsoleReader reader, User userLogged) throws IOException {
        AccountDeleteResult result;

        if (userLogged == null || userLogged.getId() <= 0) {
            result = new AccountDeleteResult(AccountDeleteStatus.INVALID_INPUT, null);

        } else {
            System.out.println(AccountMessages.CONFIRM_DELETE_ACCOUNT);
            Integer input = reader.readInteger();

            if (input == null) {
                result = new AccountDeleteResult(AccountDeleteStatus.INVALID_INPUT, userLogged.getName());

            } else {
                ConfirmationOption option = ConfirmationOption.fromInt(input);

                if (option == ConfirmationOption.YES) {
                    String deletedUserName = userLogged.getName();
                    userService.delete(userLogged);
                    result = new AccountDeleteResult(AccountDeleteStatus.ACCOUNT_DELETED, deletedUserName);

                } else if (option == ConfirmationOption.NO) {
                    result = new AccountDeleteResult(AccountDeleteStatus.ACCOUNT_NOT_DELETED, userLogged.getName());

                } else {
                    result = new AccountDeleteResult(AccountDeleteStatus.INVALID_INPUT, userLogged.getName());
                }
            }
        }
        deleteAccountHandler.handle(result);
        return result;
    }
}