package pl.edu.agh.mwo.hibernate.filealbummanager.action.friend;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.FriendService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.friend.FriendMessages;

import java.io.IOException;

public class AddFriendAction {

    private final UserService userService;
    private final FriendService friendService;

    public AddFriendAction(UserService userService, FriendService friendService) {

        this.userService = userService;
        this.friendService = friendService;
    }

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {
        if (userLogged == null)
            return MenuResult.CONTINUE;

        System.out.println(FriendMessages.ADD_FRIEND_USERNAME);

        String friendName = reader.readLine();
        if (friendName == null || friendName.isBlank()) {
            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
            return MenuResult.CONTINUE;
        }

        User friend = userService.getUser(friendName);
        if (friend == null) {
            System.out.println(String.format(AccountMessages.USER_NOT_FOUND_BY_NAME, friendName));
            return MenuResult.CONTINUE;
        }

        if (friendService.areFriends(userLogged, friend)) {
            System.out.println(String.format(FriendMessages.ALREADY_FRIEND, friendName));
        } else {
            friendService.addFriend(userLogged, friend);
            System.out.println(String.format(FriendMessages.NOW_FRIEND, friendName));
        }
        return MenuResult.CONTINUE;
    }
}