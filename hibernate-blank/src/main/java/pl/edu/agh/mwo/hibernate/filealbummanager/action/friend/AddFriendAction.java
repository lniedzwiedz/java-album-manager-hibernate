package pl.edu.agh.mwo.hibernate.filealbummanager.action.friend;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.FriendService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.friend.FriendMessages;

import java.io.BufferedReader;
import java.io.IOException;

public class AddFriendAction {

    private final UserService userService;
    private final FriendService friendService;

    public AddFriendAction(UserService userService, FriendService friendService) {
        this.userService = userService;
        this.friendService = friendService;
    }

    public void execute(BufferedReader br, User userLogged) throws IOException {
        if (userLogged == null)
            return;

        System.out.println(FriendMessages.ADD_FRIEND_USERNAME);
        String friendName = br.readLine();

        if (!userService.isUserExistsInDatabase(friendName)) {
            System.out.println(String.format(AccountMessages.USER_DOES_NOT_EXIST, friendName));
            return;
        }

        if (friendService.areWeFriends(userLogged, friendName)) {
            System.out.println(String.format(FriendMessages.ALREADY_FRIEND, friendName));
        } else {
            friendService.addFriend(userLogged, friendName);
            System.out.println(String.format(FriendMessages.NOW_FRIEND, friendName));
        }
    }
}