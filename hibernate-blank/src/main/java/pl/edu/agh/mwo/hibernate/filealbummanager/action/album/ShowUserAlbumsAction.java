package pl.edu.agh.mwo.hibernate.filealbummanager.action.album;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.FriendService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsolePrinter;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.album.AlbumMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.friend.FriendMessages;

import java.io.IOException;
import java.util.List;

public class ShowUserAlbumsAction {

    private final UserService userService;
    private final FriendService friendService;
    private final AlbumService albumService;
    private final ConsolePrinter consolePrinter;

    public ShowUserAlbumsAction(
            UserService userService,
            FriendService friendService,
            AlbumService albumService,
            ConsolePrinter consolePrinter) {

        this.userService = userService;
        this.friendService = friendService;
        this.albumService = albumService;
        this.consolePrinter = consolePrinter;
    }

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {
        consolePrinter.printMessage(AlbumMessages.ENTER_USERNAME_ALBUMS);
        String userName = reader.readLine();
        if (userName == null || userName.isBlank()) {
            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
            return MenuResult.CONTINUE;
        }

        User user = userService.getUser(userName);
        if (!friendService.areFriends(userLogged, user)) {
            System.out.println(FriendMessages.NOW_FRIEND);
            return MenuResult.CONTINUE;
        }

        List<Album> albums = albumService.getAlbums(user.getId());
        consolePrinter.printUserAlbums(user, albums);
        return MenuResult.CONTINUE;
    }
}