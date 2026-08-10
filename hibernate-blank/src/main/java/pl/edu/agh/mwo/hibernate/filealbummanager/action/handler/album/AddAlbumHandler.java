package pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.album;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.album.AlbumAddResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.album.AlbumMessages;

public class AddAlbumHandler {

    public MenuResult handleAdd(AlbumAddResult result) {
        if (result == null)
            return MenuResult.CONTINUE;

        switch (result) {
            case LOGGED_USER_NOT_FOUND:
                System.out.println(AccountMessages.LOGGED_USER_NOT_FOUND);
                break;

            case ALBUM_ADD_FORBIDDEN:
                System.out.println(AlbumMessages.ALBUM_ADD_FORBIDDEN);
                break;

            case ALBUM_ALREADY_EXISTS:
                System.out.println(AlbumMessages.ALBUM_ALREADY_EXISTS);
                break;

            case ALBUM_ADDED:
                System.out.println(AlbumMessages.ALBUM_ADDED);
                break;

            default:
                break;
        }
        return MenuResult.CONTINUE;
    }
}