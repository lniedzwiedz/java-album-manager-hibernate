package pl.edu.agh.mwo.hibernate.filealbummanager.action.friend;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.FriendService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsolePrinter;

import java.util.List;

public class ShowFriendsAction {

    private final FriendService friendService;
    private final ConsolePrinter consolePrinter;

    public ShowFriendsAction(FriendService friendService, ConsolePrinter consolePrinter) {
        this.friendService = friendService;
        this.consolePrinter = consolePrinter;
    }

    public MenuResult execute(User userLogged) {
        List<User> friends = friendService.getFriends(userLogged);
        consolePrinter.printFriends(friends);
        return MenuResult.CONTINUE;
    }
}