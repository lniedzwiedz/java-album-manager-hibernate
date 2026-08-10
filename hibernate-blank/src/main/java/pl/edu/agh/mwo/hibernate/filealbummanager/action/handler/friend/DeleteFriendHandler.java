package pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.friend;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.friend.FriendDeleteResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.friend.FriendMessages;

public class DeleteFriendHandler {

    public MenuResult handle(FriendDeleteResult result, String friendName) {
        if (result == null) return MenuResult.CONTINUE;

        switch (result) {
            case LOGGED_USER_NOT_FOUND:
                System.out.println(AccountMessages.LOGGED_USER_NOT_FOUND);
                break;

            case FRIEND_NOT_FOUND:
                System.out.println(String.format(FriendMessages.FRIEND_NOT_FOUND, friendName));
                break;

            case NOT_FRIEND:
                System.out.println(String.format(FriendMessages.NOT_FRIEND, friendName));
                break;

            case FRIEND_REMOVED:
                System.out.println(String.format(FriendMessages.FRIEND_REMOVED, friendName));
                break;

            default:
                break;
        }
        return MenuResult.CONTINUE;
    }
}