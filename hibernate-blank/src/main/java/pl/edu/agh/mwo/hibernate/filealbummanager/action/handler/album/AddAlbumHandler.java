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
            case CAN_BE_ADDED:
                System.out.println(AlbumMessages.ALBUM_ADDED);
                break;

            case ALREADY_EXISTS:
                System.out.println(AlbumMessages.ALBUM_EXISTS);
                break;

            case INVALID_USER:
                System.out.println(AccountMessages.USER_NOT_FOUND);
                break;

            default:
                break;
        }
        return MenuResult.CONTINUE;
    }
}