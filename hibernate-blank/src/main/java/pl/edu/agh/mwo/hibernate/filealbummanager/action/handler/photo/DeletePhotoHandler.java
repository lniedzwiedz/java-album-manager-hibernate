package pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.photo;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.photo.PhotoDeleteResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoMessages;

public class DeletePhotoHandler {

    public MenuResult handle(PhotoDeleteResult result, String userName) {
        if (result == null) {
            System.out.println(PhotoMessages.PHOTO_DELETE_ERROR);
            return MenuResult.CONTINUE;
        }

        switch (result) {
            case LOGGED_USER_NOT_FOUND:
                System.out.println(PhotoMessages.LOGGED_USER_NOT_FOUND);
                break;

            case ALBUM_DATA_NOT_FOUND:
                System.out.println(PhotoMessages.ALBUM_DATA_NOT_FOUND);
                break;

            case ALBUM_NOT_FOUND:
                System.out.println(PhotoMessages.ALBUM_NOT_FOUND);
                break;

            case PHOTO_DATA_NOT_FOUND:
                System.out.println(PhotoMessages.PHOTO_DATA_NOT_FOUND);
                break;

            case PHOTO_NOT_FOUND:
                System.out.println(PhotoMessages.PHOTO_NOT_FOUND);
                break;

            case PHOTO_DELETED:
                System.out.println(PhotoMessages.PHOTO_DELETED);
                break;

            default:
                System.out.println(PhotoMessages.PHOTO_DELETE_ERROR);
                break;
        }
        return MenuResult.CONTINUE;
    }
}