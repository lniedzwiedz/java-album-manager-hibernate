package pl.edu.agh.mwo.hibernate.filealbummanager.action.friend;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.FriendService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.friend.FriendMessages;

import java.io.BufferedReader;
import java.io.IOException;

public class DeleteFriendAction {

    private final UserService userService;
    private final FriendService friendService;

    public DeleteFriendAction(UserService userService, FriendService friendService) {
        this.userService = userService;
        this.friendService = friendService;
    }

    public void execute(ConsoleReader reader, User userLogged) throws IOException {
        if (userLogged == null)
            return;

        System.out.println(FriendMessages.DELETE_FRIEND_USERNAME);
        String friendName = reader.readLine();

        if (!userService.isUserExistsInDatabase(friendName)) {
            System.out.println(String.format(AccountMessages.USER_NOT_FOUND_BY_NAME, friendName));
            return;
        }

        if (friendService.areWeFriends(userLogged, friendName)) {
            friendService.deleteFriend(userLogged, friendName);
            System.out.println(String.format(FriendMessages.FRIEND_REMOVED, friendName));
        } else {
            System.out.println(String.format(FriendMessages.NOT_FRIEND, friendName));
        }
    }
}