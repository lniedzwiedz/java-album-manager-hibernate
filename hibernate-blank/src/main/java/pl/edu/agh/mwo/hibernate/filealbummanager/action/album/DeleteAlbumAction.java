package pl.edu.agh.mwo.hibernate.filealbummanager.action.album;

import pl.edu.agh.mwo.hibernate.filealbummanager.handler.album.DeleteAlbumHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.menu.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.album.DeleteAlbumResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.status.album.DeleteAlbumStatus;
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

        System.out.println(AlbumMessages.DELETE_ALBUM_NAME);
        String albumName = reader.readLine();

        DeleteAlbumStatus status = albumService.deleteAlbum(userLogged, albumName);
        DeleteAlbumResult result = new DeleteAlbumResult(status, albumName);

        deleteAlbumHandler.handle(result);
        return MenuResult.CONTINUE;
    }
}