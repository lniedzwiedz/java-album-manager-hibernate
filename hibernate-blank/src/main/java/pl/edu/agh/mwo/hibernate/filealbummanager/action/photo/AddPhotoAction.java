package pl.edu.agh.mwo.hibernate.filealbummanager.action.photo;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.photo.PhotoMessages;

import java.io.BufferedReader;
import java.io.IOException;

public class AddPhotoAction {

    private final AlbumService albumService;
    private final PhotoService photoService;

    public AddPhotoAction(AlbumService albumService, PhotoService photoService) {
        this.albumService = albumService;
        this.photoService = photoService;
    }

    public void execute(BufferedReader br, User userLogged) throws IOException {
        if (userLogged == null)
            return;

        System.out.println(PhotoMessages.ENTER_ALBUM_ADD_PHOTO);
        String albumName = br.readLine();

        if (!albumService.isAlbumBelongToUser(userLogged, albumName)) {
            System.out.println(String.format(PhotoMessages.PHOTO_ADD_FORBIDDEN, userLogged.getName()));
            return;
        }

        System.out.println(PhotoMessages.ADD_PHOTO_NAME);
        String photoName = br.readLine();
        int photoResult = photoService.getProcessingStatusWhileAddingPhoto(userLogged, albumName, photoName);

        switch (photoResult) {
            case 1 -> {
                photoService.addPhoto(photoName, albumName, userLogged);
                System.out.println(PhotoMessages.PHOTO_ADDED);
            }

            case 2 -> System.out.println(PhotoMessages.PHOTO_EXISTS);
            default -> {
                // Upsss to fix.
            }
        }
    }
}