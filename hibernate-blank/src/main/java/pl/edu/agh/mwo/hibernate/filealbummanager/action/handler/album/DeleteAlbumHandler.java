package pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.album;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.album.AlbumDeleteResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.account.AccountMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.album.AlbumMessages;

public class DeleteAlbumHandler {

    public MenuResult handleDelete(AlbumDeleteResult result) {
        if (result == null)
            return MenuResult.CONTINUE;

        switch (result) {
            case CAN_BE_DELETED:
                System.out.println(AlbumMessages.ALBUM_REMOVED);
                break;

            case ALBUM_NOT_FOUND:
                System.out.println(AlbumMessages.ALBUM_DOES_NOT_EXIST);
                break;

            case DELETE_FORBIDDEN:
                System.out.println(AlbumMessages.ALBUM_DELETE_FORBIDDEN);
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