package pl.edu.agh.mwo.hibernate.filealbummanager.action.friend;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.FriendManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.Messages;

import java.io.BufferedReader;
import java.io.IOException;

public class AddFriendAction {

    private final UserManagerService userManager;
    private final FriendManagerService friendManager;

    public AddFriendAction(UserManagerService userManager, FriendManagerService friendManager) {
        this.userManager = userManager;
        this.friendManager = friendManager;
    }

    public void execute(BufferedReader br, User userLogged) throws IOException {
        System.out.println(Messages.ADD_FRIEND_USERNAME);
        String friendName = br.readLine();
        if (!userManager.isUserExistsInDatabase(friendName)) {
            System.out.println(String.format(Messages.USER_DOES_NOT_EXIST, friendName));
            return;
        }
        if (friendManager.areWeFriends(userLogged, friendName)) {
            System.out.println(String.format(Messages.ALREADY_FRIEND, friendName));
        } else {
            friendManager.addFriend(userLogged, friendName);
            System.out.println(String.format(Messages.NOW_FRIEND, friendName));
        }
    }
}