package pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.album;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.album.AlbumDeleteResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.album.AlbumMessages;

public class DeleteAlbumHandler {

    public MenuResult handleDelete(AlbumDeleteResult result) {
        if (result == null) {
            System.out.println(AlbumMessages.ALBUM_DELETE_ERROR);
            return MenuResult.CONTINUE;
        }

        switch (result) {
            case LOGGED_USER_NOT_FOUND:
                System.out.println(AlbumMessages.LOGGED_USER_NOT_FOUND);
                break;

            case ALBUM_DATA_NOT_FOUND:
                System.out.println(AlbumMessages.ALBUM_DATA_NOT_FOUND);
                break;

            case ALBUM_NOT_FOUND:
                System.out.println(AlbumMessages.ALBUM_NOT_FOUND);
                break;

            case ALBUM_NOT_OWNED_BY_USER:
                System.out.println(AlbumMessages.ALBUM_NOT_OWNED_BY_USER);
                break;

            case ALBUM_DELETED:
                System.out.println(AlbumMessages.ALBUM_DELETED);
                break;

            default:
                System.out.println(AlbumMessages.ALBUM_DELETE_ERROR);
                break;
        }
        return MenuResult.CONTINUE;
    }
}