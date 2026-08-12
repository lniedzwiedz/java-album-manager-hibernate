package pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.friend;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.friend.FriendAddResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.friend.FriendMessages;

public class AddFriendHandler {

    public void handle(FriendAddResult result) {
        if (result == null)
            return;

        switch (result.getStatus()) {
            case NOW_FRIEND:
                System.out.println(String.format(FriendMessages.NOW_FRIEND, result.getUserName()));
                break;

            case ALREADY_FRIEND:
                System.out.println(String.format(FriendMessages.ALREADY_FRIEND, result.getUserName()));
                break;

            case FRIEND_NOT_FOUND:
                System.out.println(String.format(FriendMessages.FRIEND_NOT_FOUND, result.getUserName()));
                break;

            case FRIEND_DATA_NOT_FOUND:
                System.out.println(FriendMessages.FRIEND_DATA_NOT_FOUND);
                break;

            case LOGGED_USER_NOT_FOUND:
                System.out.println(FriendMessages.LOGGED_USER_NOT_FOUND);
                break;
        }
    }
}