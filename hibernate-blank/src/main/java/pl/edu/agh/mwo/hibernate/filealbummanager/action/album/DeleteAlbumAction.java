package pl.edu.agh.mwo.hibernate.filealbummanager.action.album;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.album.AlbumMessages;

import java.io.BufferedReader;
import java.io.IOException;

public class DeleteAlbumAction {

    private final AlbumService albumService;

    public DeleteAlbumAction(AlbumService albumService) {
        this.albumService = albumService;
    }

    public void execute(ConsoleReader reader, User userLogged) throws IOException {
        System.out.println(AlbumMessages.REMOVE_ALBUM_NAME);

        String albumName = reader.readLine();

        if (albumService.isAlbumBelongToUser(userLogged, albumName)) {
            albumService.deleteAlbum(userLogged, albumName);
            System.out.println(AlbumMessages.ALBUM_REMOVED);
        } else {
            System.out.println(
                    String.format(
                            AlbumMessages.ALBUM_DELETE_FORBIDDEN,
                            userLogged.getName()
                    )
            );
        }
    }
}