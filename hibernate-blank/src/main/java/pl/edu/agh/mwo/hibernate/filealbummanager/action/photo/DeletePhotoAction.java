package pl.edu.agh.mwo.hibernate.filealbummanager.action.photo;

import pl.edu.agh.mwo.hibernate.filealbummanager.handler.photo.DeletePhotoHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.menu.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.photo.DeletePhotoResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.status.photo.DeletePhotoStatus;
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

        DeletePhotoStatus status = photoService.deletePhoto(userLogged, albumName, photoName);
        DeletePhotoResult result = new DeletePhotoResult(status, photoName);

        deletePhotoHandler.handle(result);
        return MenuResult.CONTINUE;
    }
}