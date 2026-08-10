package pl.edu.agh.mwo.hibernate.filealbummanager.action.friend;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.friend.DeleteFriendHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.friend.FriendDeleteResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.FriendService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;
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
        if (userLogged == null)
            return MenuResult.CONTINUE;

        System.out.println(FriendMessages.DELETE_FRIEND_USERNAME);
        String friendName = reader.readLine();

        if (friendName == null || friendName.isBlank()) {
            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
            return MenuResult.CONTINUE;
        }

        FriendDeleteResult result = friendService.deleteFriend(userLogged, friendName);
        return deleteFriendHandler.handle(result, friendName);
    }
}