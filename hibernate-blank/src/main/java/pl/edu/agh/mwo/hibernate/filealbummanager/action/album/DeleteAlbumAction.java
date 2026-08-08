package pl.edu.agh.mwo.hibernate.filealbummanager.action.album;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.album.AlbumMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;

import java.io.IOException;

public class DeleteAlbumAction {

    private final AlbumService albumService;

    public DeleteAlbumAction(AlbumService albumService) {
        this.albumService = albumService;
    }

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {
        System.out.println(AlbumMessages.REMOVE_ALBUM_NAME);

        String albumName = reader.readLine();
        if (albumName == null || albumName.isBlank()) {
            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
            return MenuResult.CONTINUE;
        }

        if (albumService.isAlbumBelongToUser(userLogged, albumName)) {
            albumService.deleteAlbum(userLogged, albumName);
            System.out.println(AlbumMessages.ALBUM_REMOVED);
        } else {
            System.out.println(String.format(AlbumMessages.ALBUM_DELETE_FORBIDDEN, userLogged.getName()));
        }
        return MenuResult.CONTINUE;
    }
}