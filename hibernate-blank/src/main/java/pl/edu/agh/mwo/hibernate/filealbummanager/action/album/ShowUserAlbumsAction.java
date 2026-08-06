package pl.edu.agh.mwo.hibernate.filealbummanager.action.album;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsolePrinter;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.album.AlbumMessages;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class ShowUserAlbumsAction {

    private final AlbumService albumService;
    private final UserService userService;
    private final ConsolePrinter consolePrinter;

    public ShowUserAlbumsAction(AlbumService albumService, UserService userService, ConsolePrinter consolePrinter) {
        this.albumService = albumService;
        this.userService = userService;
        this.consolePrinter = consolePrinter;
    }

    public void execute(ConsoleReader reader) throws IOException {
        System.out.println(AlbumMessages.ENTER_USERNAME_ALBUMS);

        consolePrinter.printMessage( AlbumMessages.ENTER_USERNAME_ALBUMS );

        String userName = reader.readLine();
        User user = userService.getUserFromDatabase(userName);

        if (user != null) {
            String.format( AccountMessages.USER_NOT_FOUND_BY_NAME, userName );
            return;
        } else {
            System.out.println(AccountMessages.USER_NOT_FOUND);
        }

        List<Album> albums = albumService.getAlbumsFromDatabase(user.getId()); consolePrinter.printUserAlbums(user, albums);
    }
}