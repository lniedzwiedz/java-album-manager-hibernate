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
            case LOGGED_USER_NOT_FOUND:
                System.out.println(AccountMessages.LOGGED_USER_NOT_FOUND);
                break;

            case FRIEND_NOT_FOUND:
                System.out.println(String.format(FriendMessages.FRIEND_NOT_FOUND, friendName));
                break;

            case ALREADY_FRIEND:
                System.out.println(String.format(FriendMessages.ALREADY_FRIEND, friendName));
                break;

            case NOW_FRIEND:
                System.out.println(String.format(FriendMessages.NOW_FRIEND, friendName));
                break;

            default:
                break;
        }
        return MenuResult.CONTINUE;
    }
}