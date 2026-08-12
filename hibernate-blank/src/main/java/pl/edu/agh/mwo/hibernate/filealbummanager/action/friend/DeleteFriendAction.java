package pl.edu.agh.mwo.hibernate.filealbummanager.action.friend;

import pl.edu.agh.mwo.hibernate.filealbummanager.handler.friend.DeleteFriendHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.menu.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.friend.DeleteFriendResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.status.friend.DeleteFriendStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.FriendService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.friend.FriendMessages;

import java.io.IOException;

public class DeleteFriendAction {

    private final FriendService friendService;
    private final DeleteFriendHandler deleteFriendHandler;

    public DeleteFriendAction(FriendService friendService, DeleteFriendHandler deleteFriendHandler) {
        this.friendService = friendService;
        this.deleteFriendHandler = deleteFriendHandler;
    }

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {

        System.out.println(FriendMessages.DELETE_FRIEND_USERNAME);
        String friendName = reader.readLine();

        DeleteFriendStatus status = friendService.deleteFriend(userLogged, friendName);
        DeleteFriendResult result = new DeleteFriendResult(status, friendName);

        deleteFriendHandler.handle(result);
        return MenuResult.CONTINUE;
    }
}