package pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.friend;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.friend.FriendDeleteResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.friend.FriendMessages;

public class DeleteFriendHandler {

    public MenuResult handle(FriendDeleteResult result, String friendName) {
        if (result == null)
            return MenuResult.CONTINUE;

        switch (result) {
            case FRIEND_REMOVED:
                System.out.println(String.format(FriendMessages.FRIEND_REMOVED, friendName));
                break;

            case NOT_FRIEND:
                System.out.println(String.format(FriendMessages.NOT_FRIEND, friendName));
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