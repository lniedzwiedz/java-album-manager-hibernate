package pl.edu.agh.mwo.hibernate.filealbummanager.action.photo;

import pl.edu.agh.mwo.hibernate.filealbummanager.handler.photo.AddPhotoHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.menu.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.photo.AddPhotoResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.status.photo.AddPhotoStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoMessages;

import java.io.IOException;

public class AddPhotoAction {

    private final PhotoService photoService;
    private final AddPhotoHandler addPhotoHandler;

    public AddPhotoAction(PhotoService photoService, AddPhotoHandler addPhotoHandler) {
        this.photoService = photoService;
        this.addPhotoHandler = addPhotoHandler;
    }

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {

        System.out.println(PhotoMessages.ENTER_ALBUM_ADD_PHOTO);
        String albumName = reader.readLine();

        System.out.println(PhotoMessages.ADD_PHOTO_NAME);
        String photoName = reader.readLine();

        AddPhotoStatus status = photoService.addPhoto(userLogged, albumName, photoName);
        AddPhotoResult result = new AddPhotoResult(status, photoName);

        addPhotoHandler.handle(result);
        return MenuResult.CONTINUE;
    }
}