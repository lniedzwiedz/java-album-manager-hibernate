package pl.edu.agh.mwo.hibernate.filealbummanager.action.photolike;

import pl.edu.agh.mwo.hibernate.filealbummanager.handler.photolike.DeletePhotoLikeHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.menu.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.photolike.DeletePhotoLikeResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.status.photolike.DeletePhotoLikeStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoLikeService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoLikeMessages;

import java.io.IOException;

public class DeletePhotoLikeAction {

    private final PhotoLikeService photoLikeService;
    private final DeletePhotoLikeHandler deletePhotoLikeHandler;

    public DeletePhotoLikeAction(PhotoLikeService photoLikeService, DeletePhotoLikeHandler deletePhotoLikeHandler) {
        this.photoLikeService = photoLikeService;
        this.deletePhotoLikeHandler = deletePhotoLikeHandler;
    }

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {

        System.out.println(PhotoLikeMessages.PHOTO_OWNER_USERNAME);
        String friendName = reader.readLine();

        System.out.println(PhotoLikeMessages.ALBUM_NAME_LIKE);
        String albumName = reader.readLine();

        System.out.println(PhotoLikeMessages.REMOVE_PHOTO_LIKE_NAME);
        String photoName = reader.readLine();

        DeletePhotoLikeStatus status = photoLikeService.deletePhotoLike(userLogged, friendName, albumName, photoName);
        DeletePhotoLikeResult result = new DeletePhotoLikeResult(status, friendName, albumName, photoName);

        deletePhotoLikeHandler.handle(result);
        return MenuResult.CONTINUE;
    }
}