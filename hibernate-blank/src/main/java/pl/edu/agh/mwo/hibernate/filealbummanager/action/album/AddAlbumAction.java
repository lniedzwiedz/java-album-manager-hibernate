package pl.edu.agh.mwo.hibernate.filealbummanager.action.album;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.album.AddAlbumHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.album.AlbumAddResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.album.AlbumMessages;

import java.io.IOException;

public class AddAlbumAction {

    private final AlbumService albumService;
    private final AddAlbumHandler albumHandler;

    public AddAlbumAction(AlbumService albumService, AddAlbumHandler albumHandler) {

        this.albumService = albumService;
        this.albumHandler = albumHandler;
    }

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {
        if (userLogged == null)
            return MenuResult.CONTINUE;

        System.out.println(AlbumMessages.ADD_ALBUM_NAME);
        String albumName = reader.readLine();

        if (albumName == null || albumName.isBlank()) {
//            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
            System.out.println(AlbumMessages.ALBUM_DATA_NOT_FOUND);
            return MenuResult.CONTINUE;
        }

        AlbumAddResult result = albumService.addAlbum(userLogged, albumName);
        return albumHandler.handleAdd(result);
    }
}