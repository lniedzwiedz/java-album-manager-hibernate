package pl.edu.agh.mwo.hibernate.filealbummanager.action.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.option.ConfirmationOption;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;

import java.io.IOException;

public class DeleteAccountAction {

    private final UserService userService;

    public DeleteAccountAction(UserService userService) {
        this.userService = userService;
    }

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {
        if (userLogged == null)
            return MenuResult.CONTINUE;

        System.out.println(AccountMessages.CONFIRM_DELETE_ACCOUNT);

        Integer deleteValue = reader.readInteger();
        if (deleteValue == null) {
            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
            return MenuResult.CONTINUE;
        }

        ConfirmationOption deleteOption = ConfirmationOption.fromInt(deleteValue);

        if (deleteOption == ConfirmationOption.YES) {
            String deletedUserName = userLogged.getName();
            userService.deleteUser(userLogged);
            System.out.println(String.format(AccountMessages.GOODBYE, deletedUserName));
            System.out.println(AccountMessages.ACCOUNT_DELETED);
            return MenuResult.EXIT;
        } else if (deleteOption == ConfirmationOption.NO) {
            System.out.println(AccountMessages.ACCOUNT_NOT_DELETED);
        } else {
            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
        }
        return MenuResult.CONTINUE;
    }
}