package pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.album;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.album.AlbumAddResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.album.AlbumMessages;

public class AddAlbumHandler {

    public MenuResult handleAdd(AlbumAddResult result) {
        if (result == null) {
            System.out.println(AlbumMessages.ALBUM_ADD_ERROR);
            return MenuResult.CONTINUE;
        }

        switch (result) {
            case LOGGED_USER_NOT_FOUND:
                System.out.println(AlbumMessages.LOGGED_USER_NOT_FOUND);
                break;

            case ALBUM_DATA_NOT_FOUND:
                System.out.println(AlbumMessages.ALBUM_DATA_NOT_FOUND);
                break;

            case ALBUM_ALREADY_EXISTS:
                System.out.println(AlbumMessages.ALBUM_ALREADY_EXISTS);
                break;

            case ALBUM_ADDED:
                System.out.println(AlbumMessages.ALBUM_ADDED);
                break;

            default:
                System.out.println(AlbumMessages.ALBUM_ADD_ERROR);
                break;
        }
        return MenuResult.CONTINUE;
    }
}