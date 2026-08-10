package pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.friend;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.friend.FriendAddResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.friend.FriendMessages;

public class AddFriendHandler {

    public MenuResult handle(FriendAddResult result, String friendName) {
        if (result == null) {
            System.out.println(FriendMessages.FRIEND_ADD_ERROR);
            return MenuResult.CONTINUE;
        }

        switch (result) {
            case LOGGED_USER_NOT_FOUND:
                System.out.println(FriendMessages.LOGGED_USER_NOT_FOUND);
                break;

            case FRIEND_DATA_NOT_FOUND:
                System.out.println(FriendMessages.FRIEND_DATA_NOT_FOUND);
                break;

            case FRIEND_NOT_FOUND:
                System.out.println(FriendMessages.FRIEND_NOT_FOUND);
                break;

            case ALREADY_FRIEND:
                System.out.println(FriendMessages.ALREADY_FRIEND);
                break;

            case NOW_FRIEND:
                System.out.println(FriendMessages.NOW_FRIEND);
                break;

            default:
                System.out.println(FriendMessages.FRIEND_ADD_ERROR);
                break;
        }
        return MenuResult.CONTINUE;
    }
}