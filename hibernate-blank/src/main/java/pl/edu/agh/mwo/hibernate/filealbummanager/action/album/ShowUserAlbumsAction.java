package pl.edu.agh.mwo.hibernate.filealbummanager.action.album;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.album.AlbumMessages;

import java.io.BufferedReader;
import java.io.IOException;

public class ShowUserAlbumsAction {

    private final AlbumService albumService;
    private final UserService userService;

    public ShowUserAlbumsAction(AlbumService albumService, UserService userService) {
        this.albumService = albumService;
        this.userService = userService;
    }

    public void execute(BufferedReader br) throws IOException {
        System.out.println(AlbumMessages.ENTER_USERNAME_ALBUMS);

        String userName = br.readLine();
        User user = userService.getUserFromDatabase(userName);

        if (user != null) {
            albumService.printUserAlbums(user);
        } else {
            System.out.println(AccountMessages.USER_NOT_FOUND);
        }
    }
}