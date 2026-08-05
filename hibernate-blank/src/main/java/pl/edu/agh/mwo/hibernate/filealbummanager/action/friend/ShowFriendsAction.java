package pl.edu.agh.mwo.hibernate.filealbummanager.action.friend;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.FriendService;

public class ShowFriendsAction {

    private final FriendService friendService;

    public ShowFriendsAction(FriendService friendService) {
        this.friendService = friendService;
    }

    public void execute(User userLogged) {
        friendService.printMyFriends(userLogged);
    }
}