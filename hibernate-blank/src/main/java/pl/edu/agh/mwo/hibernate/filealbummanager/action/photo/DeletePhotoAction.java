package pl.edu.agh.mwo.hibernate.filealbummanager.action.photo;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.photo.DeletePhotoHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.photo.PhotoDeleteResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.photo.PhotoDeleteStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
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

        System.out.println(PhotoMessages.ENTER_ALBUM_ADD_PHOTO);
        String albumName = reader.readLine();

        System.out.println(PhotoMessages.DELETE_PHOTO_NAME);
        String photoName = reader.readLine();

        PhotoDeleteStatus status = photoService.deletePhoto(userLogged, albumName, photoName);
        PhotoDeleteResult result = new PhotoDeleteResult(status, photoName);

        deletePhotoHandler.handle(result);
        return MenuResult.CONTINUE;
    }
}