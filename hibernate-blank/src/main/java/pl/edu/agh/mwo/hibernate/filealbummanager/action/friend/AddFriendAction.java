package pl.edu.agh.mwo.hibernate.filealbummanager.action.friend;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.friend.AddFriendHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.friend.FriendAddResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.FriendService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.friend.FriendMessages;

import java.io.IOException;

public class AddFriendAction {

    private final UserService userService;
    private final FriendService friendService;
    private final AddFriendHandler addFriendHandler;

    public AddFriendAction(UserService userService, FriendService friendService, AddFriendHandler addFriendHandler) {
        this.userService = userService;
        this.friendService = friendService;
        this.addFriendHandler = addFriendHandler;
    }

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {
        if (userLogged == null || userLogged.getId() <= 0) {
            System.out.println(FriendMessages.LOGGED_USER_NOT_FOUND);
            return MenuResult.CONTINUE;
        }

        System.out.println(FriendMessages.ADD_FRIEND_USERNAME);
        String friendName = reader.readLine();

        if (friendName == null || friendName.isBlank()) {
            System.out.println(FriendMessages.FRIEND_DATA_NOT_FOUND);
            return MenuResult.CONTINUE;
        }

        User friend = userService.getUser(friendName);
        if (friend == null || friend.getId() <= 0) {
            System.out.println(FriendMessages.USER_NOT_FOUND);
            return MenuResult.CONTINUE;
        }

        boolean areFriends = userLogged.getId() == friend.getId() ||
                userLogged.getUsers().contains(friend) ||
                friend.getUsers().contains(userLogged);

        if (areFriends) {
            System.out.println(FriendMessages.ALREADY_FRIEND);
            return MenuResult.CONTINUE;
        }

        FriendAddResult result = friendService.addFriend(userLogged, friendName);
        return addFriendHandler.handle(result, friendName);
    }
}