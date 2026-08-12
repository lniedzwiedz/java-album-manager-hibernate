package pl.edu.agh.mwo.hibernate.filealbummanager.action.friend;

import pl.edu.agh.mwo.hibernate.filealbummanager.handler.friend.AddFriendHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.friend.AddFriendResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.status.friend.AddFriendStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.menu.MenuResult;
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

        System.out.println(FriendMessages.ADD_FRIEND_USERNAME);
        String friendName = reader.readLine();

        AddFriendStatus status = friendService.addFriend(userLogged, friendName);

        AddFriendResult result = new AddFriendResult(status, friendName);

        addFriendHandler.handle(result);
        return MenuResult.CONTINUE;
    }
}