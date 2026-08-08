package pl.edu.agh.mwo.hibernate.filealbummanager.action.photo;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.PhotoAddResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoMessages;

import java.io.IOException;

public class AddPhotoAction {

    private final AlbumService albumService;
    private final PhotoService photoService;

    public AddPhotoAction(AlbumService albumService, PhotoService photoService) {
        this.albumService = albumService;
        this.photoService = photoService;
    }

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {
        if (userLogged == null)
            return MenuResult.CONTINUE;

        System.out.println(PhotoMessages.ENTER_ALBUM_ADD_PHOTO);

        String albumName = reader.readLine();
        if (albumName == null || albumName.isBlank()) {
            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
            return MenuResult.CONTINUE;
        }

        if (!albumService.isAlbumBelongToUser(userLogged, albumName)) {
            System.out.println(String.format(PhotoMessages.PHOTO_ADD_FORBIDDEN, userLogged.getName()));
            return MenuResult.CONTINUE;
        }

        System.out.println(PhotoMessages.ADD_PHOTO_NAME);
        String photoName = reader.readLine();
        PhotoAddResult result = photoService.getProcessingStatusWhileAddingPhoto(
                userLogged,
                albumName,
                photoName);

        switch (result) {

            case CAN_BE_ADDED -> {
                photoService.addPhoto(photoName, albumName, userLogged);
                System.out.println(PhotoMessages.PHOTO_ADDED);
            }

            case ALREADY_EXISTS ->
                    System.out.println(PhotoMessages.PHOTO_EXISTS);

            case INVALID_USER_OR_ALBUM ->
                    System.out.println(String.format(PhotoMessages.PHOTO_ADD_FORBIDDEN, userLogged.getName()));

            default ->
                    System.out.println(PhotoMessages.PHOTO_ADD_ERROR);
        }
        return MenuResult.CONTINUE;
    }
}