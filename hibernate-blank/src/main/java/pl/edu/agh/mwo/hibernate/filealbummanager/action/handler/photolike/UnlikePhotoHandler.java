package pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.photolike;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Photo;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.photolike.PhotoLikeStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoLikeService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.album.AlbumMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoLikeMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoMessages;

public class UnlikePhotoHandler {

    private final PhotoLikeService photoLikeService;

    public UnlikePhotoHandler(PhotoLikeService photoLikeService) {
        this.photoLikeService = photoLikeService;
    }

    public MenuResult handle(PhotoLikeStatus status, Photo photo, User userLogged) {
        if (status == null) {
            System.out.println(PhotoLikeMessages.PHOTO_LIKE_ERROR);
            return MenuResult.CONTINUE;
        }

        switch (status) {
            case NEVER_LIKED:
                System.out.println(PhotoLikeMessages.NEVER_LIKED_PHOTO);
                break;

            case ALREADY_LIKED:
                boolean deleted = photoLikeService.deletePhotoLike(userLogged, photo);
                if (deleted) {
                    System.out.println(PhotoLikeMessages.PHOTO_LIKE_REMOVED);
                } else {
                    System.out.println(PhotoLikeMessages.PHOTO_LIKE_ERROR);
                }
                break;

            case PHOTO_NOT_IN_ALBUM:
                System.out.println(PhotoMessages.PHOTO_NOT_IN_ALBUM);
                break;

            case ALBUM_DOES_NOT_EXIST:
                System.out.println(AlbumMessages.ALBUM_NOT_FOUND);
                break;

            case NOT_FRIEND_PHOTO_OWNER:
                System.out.println(PhotoLikeMessages.NOT_FRIEND_PHOTO_OWNER_NO_LIKE);
                break;

            case PHOTO_LIKE_ERROR:
            default:
                System.out.println(PhotoLikeMessages.PHOTO_LIKE_ERROR);
                break;
        }
        return MenuResult.CONTINUE;
    }
}