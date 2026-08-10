package pl.edu.agh.mwo.hibernate.filealbummanager.action.photo;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.photo.DeletePhotoHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.photo.PhotoDeleteResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.album.AlbumMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoMessages;

import java.io.IOException;

public class DeletePhotoAction {

    private final PhotoService photoService;
    private final DeletePhotoHandler deletePhotoHandler;

    public DeletePhotoAction(PhotoService photoService, DeletePhotoHandler deletePhotoHandler) {
        this.photoService = photoService;
        this.deletePhotoHandler = deletePhotoHandler;
    }

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {
        if (userLogged == null) return MenuResult.CONTINUE;

        System.out.println(PhotoMessages.DELETE_PHOTO_NAME);
        String photoName = reader.readLine();

        if (photoName == null || photoName.isBlank()) {
//            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
            System.out.println(PhotoMessages.ALBUM_DATA_NOT_FOUND);
            return MenuResult.CONTINUE;
        }

        System.out.println(AlbumMessages.ALBUM_NAME);
        String albumName = reader.readLine();

        if (albumName == null || albumName.isBlank()) {
//            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
            System.out.println(PhotoMessages.ALBUM_DATA_NOT_FOUND);
            return MenuResult.CONTINUE;
        }

        PhotoDeleteResult result = photoService.deletePhoto(userLogged, albumName, photoName);
        return deletePhotoHandler.handle(result, userLogged.getName());
    }
}