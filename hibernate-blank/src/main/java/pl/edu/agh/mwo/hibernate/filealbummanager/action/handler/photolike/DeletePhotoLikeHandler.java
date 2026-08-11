package pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.photolike;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.photolike.PhotoLikeDeleteResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoLikeMessages;

public class DeletePhotoLikeHandler {

    public MenuResult handle(PhotoLikeDeleteResult result) {
        if (result == null) {
            System.out.println(PhotoLikeMessages.PHOTO_LIKE_ERROR);
            return MenuResult.CONTINUE;
        }

        switch (result) {
            case LOGGED_USER_NOT_FOUND:
                System.out.println(PhotoLikeMessages.LOGGED_USER_NOT_FOUND);
                break;

            case USER_NOT_FOUND:
                System.out.println(PhotoLikeMessages.USER_NOT_FOUND);
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
                System.out.println(PhotoLikeMessages.ALBUM_NOT_FOUND);
                break;

            case PHOTO_DATA_NOT_FOUND:
                System.out.println(PhotoLikeMessages.PHOTO_OWNER_NOT_FOUND);
                break;

            case PHOTO_NOT_FOUND:
                System.out.println(PhotoLikeMessages.PHOTO_NOT_FOUND);
                break;

            case PHOTO_NOT_IN_ALBUM:
                System.out.println(PhotoLikeMessages.PHOTO_NOT_IN_ALBUM);
                break;

            case PHOTO_NOT_LIKED:
                System.out.println(PhotoLikeMessages.NEVER_LIKED_PHOTO);
                break;

            case PHOTO_LIKE_DELETED:
                System.out.println(PhotoLikeMessages.PHOTO_LIKE_REMOVED);
                break;

            default:
                System.out.println(PhotoLikeMessages.PHOTO_LIKE_ERROR);
                break;
        }

        return MenuResult.CONTINUE;
    }
}