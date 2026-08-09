package pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.photo;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.photo.PhotoDeleteResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoMessages;

public class DeletePhotoHandler {

    public MenuResult handle(PhotoDeleteResult result, String userName) {
        if (result == null)
            return MenuResult.CONTINUE;

        switch (result) {
            case PHOTO_DELETED:
                System.out.println(PhotoMessages.PHOTO_DELETED);
                break;

            case PHOTO_NOT_FOUND:
                System.out.println(PhotoMessages.PHOTO_NOT_FOUND);
                break;

            case DELETE_FORBIDDEN:
                System.out.println(String.format(PhotoMessages.PHOTO_DELETE_FORBIDDEN, userName));
                break;

            case ALBUM_NOT_FOUND:
                System.out.println(PhotoMessages.ALBUM_NOT_FOUND);
                break;

            default:
                break;
        }
        return MenuResult.CONTINUE;
    }
}