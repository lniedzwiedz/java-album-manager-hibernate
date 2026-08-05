package pl.edu.agh.mwo.hibernate.filealbummanager.action.album;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;

public class ShowMyAlbumsAction {

    private final AlbumService albumService;

    public ShowMyAlbumsAction(AlbumService albumService) {
        this.albumService = albumService;
    }

    public void execute(User userLogged) {
        if (userLogged == null)
            return;

        albumService.printMyAlbums(userLogged);
    }
}