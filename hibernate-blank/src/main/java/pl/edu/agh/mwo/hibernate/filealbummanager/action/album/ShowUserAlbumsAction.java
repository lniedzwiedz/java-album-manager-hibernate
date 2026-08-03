package pl.edu.agh.mwo.hibernate.filealbummanager.action.album;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.Messages;

import java.io.BufferedReader;
import java.io.IOException;

public class ShowUserAlbumsAction {

    private final AlbumManagerService albumManager;
    private final UserManagerService userManager;

    public ShowUserAlbumsAction(AlbumManagerService albumManager, UserManagerService userManager) {
        this.albumManager = albumManager;
        this.userManager = userManager;
    }

    public void execute(BufferedReader br) throws IOException {
        System.out.println(Messages.ENTER_USERNAME_ALBUMS);
        String userName = br.readLine();
        User user = userManager.getUserFromDatabase(userName);
        if (user != null) {
            albumManager.printUserAlbums(user);
        } else {
            System.out.println(Messages.USER_NOT_FOUND);
        }
    }
}