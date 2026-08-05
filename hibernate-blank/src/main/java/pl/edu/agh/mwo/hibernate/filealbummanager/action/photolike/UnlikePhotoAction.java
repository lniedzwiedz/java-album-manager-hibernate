package pl.edu.agh.mwo.hibernate.filealbummanager.action.photolike;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.album.AlbumMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.photo.PhotoLikeMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.PhotoLikeStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.photo.PhotoMessages;

import java.io.BufferedReader;
import java.io.IOException;

public class UnlikePhotoAction {

    private final PhotoService photoService;

    public UnlikePhotoAction(PhotoService photoService) {
        this.photoService = photoService;
    }

    public void execute(BufferedReader br, User userLogged) throws IOException {
        if (userLogged == null)
            return;

        System.out.println(PhotoLikeMessages.REMOVE_PHOTO_LIKE_NAME);
        String photoName = br.readLine();
        System.out.println(AlbumMessages.ALBUM_NAME);
        String albumName = br.readLine();
        PhotoLikeStatus unlikeResult = photoService.getProcessingStatusForPhotoLike(userLogged, albumName, photoName);

        if (unlikeResult == null) {
            System.out.println(PhotoLikeMessages.PHOTO_LIKE_ERROR);
            return;
        }

        switch (unlikeResult) {

            case NEVER_LIKED:
                System.out.println(PhotoLikeMessages.NEVER_LIKED_PHOTO);
                break;

            case ALREADY_LIKED:
                photoService.deletePhotoLike(userLogged, albumName, photoName);
                System.out.println(PhotoLikeMessages.PHOTO_LIKE_REMOVED);
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

            default:
                System.out.println(PhotoLikeMessages.PHOTO_LIKE_ERROR);
                break;
        }
    }
}