package pl.edu.agh.mwo.hibernate.filealbummanager.action.photo;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.album.AlbumMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.photo.PhotoMessages;

import java.io.BufferedReader;
import java.io.IOException;

public class DeletePhotoAction {

    private final PhotoService photoService;

    public DeletePhotoAction(PhotoService photoService) {
        this.photoService = photoService;
    }

    public void execute(BufferedReader br, User userLogged) throws IOException {
        if (userLogged == null)
            return;

        System.out.println(PhotoMessages.REMOVE_PHOTO_NAME);
        String photoName = br.readLine();
        System.out.println(AlbumMessages.ALBUM_NAME);

        String albumName = br.readLine();
        boolean photoBelongs = photoService.isPhotoBelongToUser(userLogged, albumName, photoName);

        if (photoBelongs) {
            photoService.deletePhoto(photoName, albumName, userLogged);
            System.out.println(PhotoMessages.PHOTO_DELETED);
        } else {
            System.out.println(String.format(PhotoMessages.PHOTO_DELETE_FORBIDDEN, userLogged.getName()));
        }
    }
}