package pl.edu.agh.mwo.hibernate.filealbummanager.action.friend;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.FriendManagerService;

public class ShowFriendsAction {

    private final FriendManagerService friendManager;

    public ShowFriendsAction(FriendManagerService friendManager) {
        this.friendManager = friendManager;
    }

    public void execute(User userLogged) {
        friendManager.printMyFriends(userLogged);
    }
}