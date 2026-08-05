package pl.edu.agh.mwo.hibernate.filealbummanager.action.photolike;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.photo.PhotoLikeMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.PhotoLikeStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.photo.PhotoMessages;

import java.io.BufferedReader;
import java.io.IOException;

public class LikePhotoAction {

    private final PhotoService photoService;

    public LikePhotoAction(PhotoService photoService) {
        this.photoService = photoService;
    }

    public void execute(BufferedReader br, User userLogged) throws IOException {
        if (userLogged == null)
            return;

        System.out.println(PhotoLikeMessages.ADD_LIKE_PHOTO_NAME);
        String photoName = br.readLine();
        System.out.println(PhotoLikeMessages.ALBUM_NAME_LIKE);
        String albumName = br.readLine();
        PhotoLikeStatus likeResult = photoService.getProcessingStatusForPhotoLike(userLogged, albumName, photoName);

        if (likeResult == null) {
            System.out.println(PhotoLikeMessages.PHOTO_LIKE_ERROR);
            return;
        }

        switch (likeResult) {

            case NEVER_LIKED:
                photoService.addPhotoLike(userLogged, albumName, photoName);
                System.out.println(PhotoLikeMessages.PHOTO_LIKE_ADDED);
                break;

            case ALREADY_LIKED:
                System.out.println(String.format(PhotoLikeMessages.ALREADY_LIKE_PHOTO, userLogged.getName()));
                break;

            case PHOTO_NOT_IN_ALBUM:
                System.out.println(PhotoMessages.PHOTO_NOT_IN_ALBUM);
                break;

            case ALBUM_DOES_NOT_EXIST:
                System.out.println(PhotoMessages.ALBUM_DOES_NOT_EXIST);
                break;

            case NOT_FRIEND_PHOTO_OWNER:
                System.out.println(PhotoLikeMessages.NOT_FRIEND_PHOTO_OWNER);
                break;

            default:
                System.out.println(PhotoLikeMessages.PHOTO_LIKE_ERROR);
                break;
        }
    }
}