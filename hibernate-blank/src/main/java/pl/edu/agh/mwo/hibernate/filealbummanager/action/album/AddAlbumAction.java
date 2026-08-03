package pl.edu.agh.mwo.hibernate.filealbummanager.action.album;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.Messages;

import java.io.BufferedReader;
import java.io.IOException;

public class AddAlbumAction {

    private final AlbumManagerService albumManager;

    public AddAlbumAction(AlbumManagerService albumManager) {
        this.albumManager = albumManager;
    }

    public void execute(BufferedReader br, User userLogged) throws IOException {
        System.out.println(Messages.ADD_ALBUM_NAME);
        String albumName = br.readLine();
        int albumResult = albumManager.getProcessingStatusWhileAddingAlbum(userLogged, albumName);
        if (albumResult == 1) {
            albumManager.createNewAlbum(userLogged, albumName);
            System.out.println(Messages.ALBUM_ADDED);
        } else if (albumResult == 2) {
            System.out.println(Messages.ALBUM_EXISTS);
        }
    }
}