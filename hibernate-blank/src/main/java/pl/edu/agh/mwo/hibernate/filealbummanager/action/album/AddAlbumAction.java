package pl.edu.agh.mwo.hibernate.filealbummanager.action.album;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.album.AddAlbumHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.album.AlbumAddResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.album.AlbumAddStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.album.AlbumMessages;

import java.io.IOException;

public class AddAlbumAction {

    private final AlbumService albumService;
    private final AddAlbumHandler addAlbumHandler;

    public AddAlbumAction(AlbumService albumService, AddAlbumHandler addAlbumHandler) {
        this.albumService = albumService;
        this.addAlbumHandler = addAlbumHandler;
    }

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {

        System.out.println(AlbumMessages.ADD_ALBUM_NAME);
        String albumName = reader.readLine();

        AlbumAddStatus status = albumService.addAlbum(userLogged, albumName);
        AlbumAddResult result = new AlbumAddResult(status, albumName);

        addAlbumHandler.handle(result);
        return MenuResult.CONTINUE;
    }
}