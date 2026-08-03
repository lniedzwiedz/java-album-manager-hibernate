package pl.edu.agh.mwo.hibernate.filealbummanager.action.album;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.Messages;

import java.io.BufferedReader;
import java.io.IOException;

public class DeleteAlbumAction {

    private final AlbumManagerService albumManager;

    public DeleteAlbumAction(AlbumManagerService albumManager) {
        this.albumManager = albumManager;
    }

    public void execute(BufferedReader br, User userLogged) throws IOException {
        System.out.println(Messages.REMOVE_ALBUM_NAME);
        String albumName = br.readLine();
        if (albumManager.isAlbumBelongToUser(userLogged, albumName)) {
            albumManager.deleteAlbum(userLogged, albumName);
            System.out.println(Messages.ALBUM_REMOVED);
        } else {
            System.out.println(String.format(Messages.ALBUM_DELETE_FORBIDDEN, userLogged.getName()));
        }
    }
}