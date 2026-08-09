package pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.friend;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.friend.FriendAddResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.friend.FriendMessages;

public class AddFriendHandler {

    public MenuResult handle(FriendAddResult result, String friendName) {
        if (result == null)
            return MenuResult.CONTINUE;

        switch (result) {
            case NOW_FRIEND:
                System.out.println(String.format(FriendMessages.NOW_FRIEND, friendName));
                break;

            case ALREADY_FRIEND:
                System.out.println(String.format(FriendMessages.ALREADY_FRIEND, friendName));
                break;

            case USER_NOT_FOUND:
                System.out.println(String.format(AccountMessages.USER_NOT_FOUND_BY_NAME, friendName));
                break;

            default:
                break;
        }
        return MenuResult.CONTINUE;
    }
}