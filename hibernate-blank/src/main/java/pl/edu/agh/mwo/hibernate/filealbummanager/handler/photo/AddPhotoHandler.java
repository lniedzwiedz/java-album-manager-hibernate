package pl.edu.agh.mwo.hibernate.filealbummanager.handler.photo;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.photo.AddPhotoResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoMessages;

public class AddPhotoHandler {

    public void handle(AddPhotoResult result) {
        if (result == null)
            return;

        switch (result.getStatus()) {
            case PHOTO_ADDED:
                System.out.println(String.format(PhotoMessages.PHOTO_ADDED, result.getPhotoName()));
                break;

            case PHOTO_ALREADY_EXISTS:
                System.out.println(String.format(PhotoMessages.PHOTO_ALREADY_EXISTS, result.getPhotoName()));
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