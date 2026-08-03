package pl.edu.agh.mwo.hibernate.filealbummanager.action.account;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.BinaryOption;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.Messages;

import java.io.BufferedReader;
import java.io.IOException;

public class DeleteAccountAction {

    private final UserManagerService userManager;

    public DeleteAccountAction(UserManagerService userManager) {
        this.userManager = userManager;
    }

    public boolean execute(BufferedReader br, User userLogged) throws IOException {
        System.out.println(Messages.CONFIRM_DELETE_ACCOUNT);
        String deleteDecision = br.readLine();
        int deleteValue;
        try {
            deleteValue = Integer.parseInt(deleteDecision);
        } catch (NumberFormatException e) {
            System.out.println(Messages.INVALID_INPUT_E3);
            return false;
        }
        BinaryOption deleteOption = BinaryOption.fromInt(deleteValue);
        if (deleteOption == BinaryOption.YES) {
            String deletedUserName = userLogged.getName();
            userManager.deleteUser(userLogged);
            System.out.println(String.format(Messages.GOODBYE, deletedUserName));
            return true;
        } else if (deleteOption == BinaryOption.NO) {
            System.out.println(Messages.WISE_CHOICE);
        } else {
            System.out.println(Messages.INVALID_INPUT_E3);
        }
        return false;
    }
}