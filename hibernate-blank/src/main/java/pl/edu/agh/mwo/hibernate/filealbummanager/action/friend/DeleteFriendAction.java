package pl.edu.agh.mwo.hibernate.filealbummanager.action.friend;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.friend.DeleteFriendHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.friend.FriendDeleteResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.friend.FriendDeleteStatus;
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

        FriendDeleteStatus status = friendService.deleteFriend(userLogged, friendName);
        FriendDeleteResult result = new FriendDeleteResult(status, friendName);

        deleteFriendHandler.handle(result);
        return MenuResult.CONTINUE;
    }
}