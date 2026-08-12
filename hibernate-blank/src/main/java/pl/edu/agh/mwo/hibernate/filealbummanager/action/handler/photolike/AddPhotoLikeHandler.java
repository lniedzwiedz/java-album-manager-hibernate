package pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.photolike;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.photolike.PhotoLikeAddResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoLikeMessages;

public class AddPhotoLikeHandler {

    public void handle(PhotoLikeAddResult result) {
        if (result == null)
            return;

        switch (result.getStatus()) {
            case LOGGED_USER_NOT_FOUND:
                System.out.println(PhotoLikeMessages.LOGGED_USER_NOT_FOUND);
                break;

            case USER_NOT_FOUND:
                System.out.println(String.format(PhotoLikeMessages.USER_NOT_FOUND, result.getUserName()));
                break;

            case FRIEND_DATA_NOT_FOUND:
                System.out.println(PhotoLikeMessages.FRIEND_DATA_NOT_FOUND);
                break;

            case NOT_FRIENDS:
                System.out.println(PhotoLikeMessages.NOT_FRIENDS);
                break;

            case ALBUM_DATA_NOT_FOUND:
                System.out.println(PhotoLikeMessages.ALBUM_DATA_NOT_FOUND);
                break;

            case ALBUM_NOT_FOUND:
                System.out.println(String.format(PhotoLikeMessages.ALBUM_NOT_FOUND, result.getAlbumName()));
                break;

            case ALBUM_NOT_OWNED_BY_USER:
                System.out.println(PhotoLikeMessages.ALBUM_NOT_OWNED_BY_USER);
                break;

            case PHOTO_DATA_NOT_FOUND:
                System.out.println(PhotoLikeMessages.PHOTO_DATA_NOT_FOUND);
                break;

            case PHOTO_NOT_FOUND:
                System.out.println(String.format(PhotoLikeMessages.PHOTO_NOT_FOUND, result.getPhotoName()));
                break;

            case PHOTO_NOT_IN_ALBUM:
                System.out.println(PhotoLikeMessages.PHOTO_NOT_IN_ALBUM);
                break;

            case PHOTO_ALREADY_LIKED:
                System.out.println(PhotoLikeMessages.ALREADY_LIKED);
                break;

            case PHOTO_LIKE_ADDED:
                System.out.println(PhotoLikeMessages.PHOTO_LIKE_ADDED);
                break;
        }
    }
}