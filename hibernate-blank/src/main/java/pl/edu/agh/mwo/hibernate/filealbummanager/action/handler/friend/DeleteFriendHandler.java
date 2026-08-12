package pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.friend;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.friend.FriendDeleteResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.friend.FriendMessages;

public class DeleteFriendHandler {

    public void handle(FriendDeleteResult result) {
        if (result == null)
            return;

        switch (result.getStatus()) {
            case LOGGED_USER_NOT_FOUND:
                System.out.println(FriendMessages.LOGGED_USER_NOT_FOUND);
                break;

            case FRIEND_NOT_FOUND:
                System.out.println(String.format(FriendMessages.FRIEND_NOT_FOUND, result.getUserName()));
                break;

            case NOT_FRIEND:
                System.out.println(String.format(FriendMessages.NOT_FRIEND, result.getUserName()));
                break;

            case FRIEND_DELETED:
                System.out.println(String.format(FriendMessages.FRIEND_DELETED, result.getUserName()));
                break;
        }
    }
}