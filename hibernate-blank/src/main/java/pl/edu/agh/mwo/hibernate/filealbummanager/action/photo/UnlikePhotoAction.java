package pl.edu.agh.mwo.hibernate.filealbummanager.action.photo;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.album.AlbumMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.photo.PhotoLikeStatus;
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

        System.out.println(PhotoMessages.REMOVE_PHOTO_LIKE_NAME);

        String photoName = br.readLine();
        System.out.println(AlbumMessages.ALBUM_NAME);

        String albumName = br.readLine();
        PhotoLikeStatus unlikeResult = PhotoLikeStatus.fromInt(photoService.getProcessingStatusForPhotoLike(userLogged, albumName, photoName));

        if (unlikeResult == PhotoLikeStatus.NEVER_LIKED) {
            System.out.println(PhotoMessages.NEVER_LIKED_PHOTO);
        } else if (unlikeResult == PhotoLikeStatus.ALREADY_LIKED) {
            photoService.deletePhotoLike(userLogged, albumName, photoName);
            System.out.println(PhotoMessages.PHOTO_LIKE_REMOVED);

        } else if (unlikeResult == PhotoLikeStatus.PHOTO_NOT_IN_ALBUM) {
            System.out.println(PhotoMessages.PHOTO_NOT_IN_ALBUM);
        } else if (unlikeResult == PhotoLikeStatus.ALBUM_DOES_NOT_EXIST) {
            System.out.println(AlbumMessages.ALBUM_NOT_FOUND);
        } else if (unlikeResult == PhotoLikeStatus.NOT_FRIEND_PHOTO_OWNER) {
            System.out.println(PhotoMessages.NOT_FRIEND_PHOTO_OWNER_NO_LIKE);
        }
    }
}