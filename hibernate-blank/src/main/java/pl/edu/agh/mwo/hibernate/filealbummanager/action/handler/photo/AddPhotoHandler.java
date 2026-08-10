package pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.photo;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.photo.PhotoAddResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoMessages;

public class AddPhotoHandler {

    public MenuResult handle(PhotoAddResult result, User userLogged) {
        if (result == null) {
            System.out.println(PhotoMessages.PHOTO_ADD_ERROR);
            return MenuResult.CONTINUE;
        }

        switch (result) {
            case PHOTO_ADDED:
                System.out.println(PhotoMessages.PHOTO_ADDED);
                break;

            case ALREADY_EXISTS:
                System.out.println(PhotoMessages.PHOTO_EXISTS);
                break;

            case INVALID_USER_OR_ALBUM:
                System.out.println(String.format(PhotoMessages.PHOTO_ADD_FORBIDDEN, userLogged.getName()));
                break;

            default:
                System.out.println(PhotoMessages.PHOTO_ADD_ERROR);
                break;
        }
        return MenuResult.CONTINUE;
    }
}