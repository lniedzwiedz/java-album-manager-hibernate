package pl.edu.agh.mwo.hibernate.filealbummanager.action.friend;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.friend.AddFriendHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.friend.FriendAddResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.FriendService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.friend.FriendMessages;

import java.io.IOException;

public class AddFriendAction {

    private final FriendService friendService;
    private final AddFriendHandler addFriendHandler;

    public AddFriendAction(FriendService friendService, AddFriendHandler addFriendHandler) {
        this.friendService = friendService;
        this.addFriendHandler = addFriendHandler;
    }

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {
        if (userLogged == null)
            return MenuResult.CONTINUE;

        System.out.println(FriendMessages.ADD_FRIEND_USERNAME);
        String friendName = reader.readLine();

        if (friendName == null || friendName.isBlank()) {
//            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
            System.out.println(FriendMessages.FRIEND_DATA_NOT_FOUND);
            return MenuResult.CONTINUE;
        }

        FriendAddResult result = friendService.addFriend(userLogged, friendName);
        return addFriendHandler.handle(result, friendName);
    }
}