package pl.edu.agh.mwo.hibernate.filealbummanager.action.album;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumManagerService;

public class ShowMyAlbumsAction {

    private final AlbumManagerService albumManager;

    public ShowMyAlbumsAction(AlbumManagerService albumManager) {
        this.albumManager = albumManager;
    }

    public void execute(User userLogged) {
        albumManager.printUserAlbums(userLogged);
    }
}