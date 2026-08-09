package pl.edu.agh.mwo.hibernate.filealbummanager.action.album;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.album.DeleteAlbumHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.album.AlbumDeleteResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.album.AlbumMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;

import java.io.IOException;

public class DeleteAlbumAction {

    private final AlbumService albumService;
    private final DeleteAlbumHandler deleteAlbumHandler;

    public DeleteAlbumAction(AlbumService albumService, DeleteAlbumHandler deleteAlbumHandler) {
        this.albumService = albumService;
        this.deleteAlbumHandler = deleteAlbumHandler;
    }

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {
        if (userLogged == null)
            return MenuResult.CONTINUE;

        System.out.println(AlbumMessages.REMOVE_ALBUM_NAME);
        String albumName = reader.readLine();

        if (albumName == null || albumName.isBlank()) {
            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
            return MenuResult.CONTINUE;
        }

        AlbumDeleteResult result = albumService.checkAlbumDeleteStatus(userLogged, albumName);
        if (result == AlbumDeleteResult.CAN_BE_DELETED) {
            albumService.deleteAlbum(userLogged, albumName);
        }
        return deleteAlbumHandler.handleDelete(result);
    }
}