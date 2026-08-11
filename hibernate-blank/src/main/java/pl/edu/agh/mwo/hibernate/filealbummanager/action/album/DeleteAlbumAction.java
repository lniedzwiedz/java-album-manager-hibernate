package pl.edu.agh.mwo.hibernate.filealbummanager.action.album;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.album.DeleteAlbumHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.album.AlbumDeleteResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.album.AlbumMessages;

import java.io.IOException;

public class DeleteAlbumAction {

    private final AlbumService albumService;
    private final DeleteAlbumHandler deleteAlbumHandler;

    public DeleteAlbumAction(AlbumService albumService, DeleteAlbumHandler deleteAlbumHandler) {
        this.albumService = albumService;
        this.deleteAlbumHandler = deleteAlbumHandler;
    }

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {
        if (userLogged == null || userLogged.getId() <= 0) {
            System.out.println(AlbumMessages.LOGGED_USER_NOT_FOUND);
            return MenuResult.CONTINUE;
        }

        System.out.println(AlbumMessages.DELETE_ALBUM_NAME);
        String albumName = reader.readLine();

        if (albumName == null || albumName.isBlank()) {
            System.out.println(AlbumMessages.ALBUM_DATA_NOT_FOUND);
            return MenuResult.CONTINUE;
        }

        Album album = albumService.getAlbum(albumName, userLogged.getId());
        if (album == null || album.getId() <= 0) {
            System.out.println(AlbumMessages.ALBUM_NOT_OWNED_BY_USER);
            return MenuResult.CONTINUE;
        }

        AlbumDeleteResult result = albumService.deleteAlbum(userLogged, album);
        return deleteAlbumHandler.handleDelete(result);
    }
}