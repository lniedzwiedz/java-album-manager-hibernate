package pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.photo;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.photo.PhotoDeleteResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoMessages;

public class DeletePhotoHandler {

    public void handle(PhotoDeleteResult result) {
        if (result == null)
            return;

        switch (result.getStatus()) {
            case PHOTO_DELETED:
                System.out.println(String.format(PhotoMessages.PHOTO_DELETED, result.getPhotoName()));
                break;

            case PHOTO_NOT_FOUND:
                System.out.println(String.format(PhotoMessages.PHOTO_NOT_FOUND, result.getPhotoName()));
                break;

            case PHOTO_NOT_IN_ALBUM:
                System.out.println(String.format(PhotoMessages.PHOTO_NOT_IN_ALBUM, result.getPhotoName()));
                break;

            case PHOTO_DATA_NOT_FOUND:
                System.out.println(PhotoMessages.PHOTO_DATA_NOT_FOUND);
                break;

            case ALBUM_DATA_NOT_FOUND:
                System.out.println(PhotoMessages.ALBUM_DATA_NOT_FOUND);
                break;

            case ALBUM_NOT_FOUND:
                System.out.println(PhotoMessages.ALBUM_NOT_FOUND);
                break;

            case ALBUM_NOT_OWNED_BY_USER:
                System.out.println(PhotoMessages.ALBUM_NOT_OWNED_BY_USER);
                break;

            case LOGGED_USER_NOT_FOUND:
                System.out.println(PhotoMessages.LOGGED_USER_NOT_FOUND);
                break;
        }
    }
}