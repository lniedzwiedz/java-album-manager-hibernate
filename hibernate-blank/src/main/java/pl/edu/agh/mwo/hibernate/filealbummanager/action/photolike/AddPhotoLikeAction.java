package pl.edu.agh.mwo.hibernate.filealbummanager.action.photolike;

import pl.edu.agh.mwo.hibernate.filealbummanager.handler.photolike.AddPhotoLikeHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.menu.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.photolike.AddPhotoLikeResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.status.photolike.AddPhotoLikeStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoLikeService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoLikeMessages;

import java.io.IOException;

public class AddPhotoLikeAction {

    private final PhotoLikeService photoLikeService;
    private final AddPhotoLikeHandler addPhotoLikeHandler;

    public AddPhotoLikeAction(PhotoLikeService photoLikeService, AddPhotoLikeHandler addPhotoLikeHandler) {
        this.photoLikeService = photoLikeService;
        this.addPhotoLikeHandler = addPhotoLikeHandler;
    }

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {

        System.out.println(PhotoLikeMessages.PHOTO_OWNER_USERNAME);
        String friendName = reader.readLine();

        System.out.println(PhotoLikeMessages.ALBUM_NAME_LIKE);
        String albumName = reader.readLine();

        System.out.println(PhotoLikeMessages.ADD_LIKE_PHOTO_NAME);
        String photoName = reader.readLine();

        AddPhotoLikeStatus status = photoLikeService.addPhotoLike(userLogged, friendName, albumName, photoName);
        AddPhotoLikeResult result = new AddPhotoLikeResult(status, friendName, albumName, photoName);

        addPhotoLikeHandler.handle(result);
        return MenuResult.CONTINUE;
    }
}