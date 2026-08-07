package pl.edu.agh.mwo.hibernate.filealbummanager.action.album;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsolePrinter;

import java.util.List;

public class ShowMyAlbumsAction {

    private final AlbumService albumService;
    private final ConsolePrinter consolePrinter;

    public ShowMyAlbumsAction(AlbumService albumService, ConsolePrinter consolePrinter) {
        this.albumService = albumService;
        this.consolePrinter = consolePrinter;
    }

    public MenuResult execute(User userLogged) {
        if (userLogged == null)
            return MenuResult.CONTINUE;

        List<Album> albums =
                albumService.getAlbumsFromDatabase(userLogged.getId());

        consolePrinter.printAlbums(albums);
        return MenuResult.CONTINUE;
    }
}