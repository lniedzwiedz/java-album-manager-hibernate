package pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.friend;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.friend.FriendDeleteResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.friend.FriendMessages;

public class DeleteFriendHandler {

    public MenuResult handle(FriendDeleteResult result, String friendName) {
        if (result == null) {
            System.out.println(FriendMessages.FRIEND_DELETE_ERROR);
            return MenuResult.CONTINUE;
        }

        switch (result) {
            case LOGGED_USER_NOT_FOUND:
                System.out.println(FriendMessages.LOGGED_USER_NOT_FOUND);
                break;

            case FRIEND_NOT_FOUND:
                System.out.println(FriendMessages.FRIEND_NOT_FOUND);
                break;

            case NOT_FRIEND:
                System.out.println(FriendMessages.NOT_FRIEND);
                break;

            case FRIEND_DELETED:
                System.out.println(FriendMessages.FRIEND_DELETED);
                break;

            default:
                System.out.println(FriendMessages.FRIEND_DELETE_ERROR);
                break;
        }
        return MenuResult.CONTINUE;
    }
}