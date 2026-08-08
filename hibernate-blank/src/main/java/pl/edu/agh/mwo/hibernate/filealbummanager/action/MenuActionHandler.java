package pl.edu.agh.mwo.hibernate.filealbummanager.action;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.option.MenuOption;

import java.io.IOException;

public class MenuActionHandler {

    private final ActionFactory actionFactory;

    public MenuActionHandler(ActionFactory actionFactory) {
        this.actionFactory = actionFactory;
    }

    public MenuResult execute(MenuOption menuOption, ConsoleReader reader, User userLogged) throws IOException {

        switch (menuOption) {

            case ADD_ALBUM:
                return actionFactory.getAddAlbumAction()
                        .execute(reader, userLogged);

            case DELETE_ALBUM:
                return actionFactory.getDeleteAlbumAction()
                        .execute(reader, userLogged);

            case SHOW_MY_ALBUMS:
                return actionFactory.getShowMyAlbumsAction()
                        .execute(userLogged);

            case SHOW_USER_ALBUMS:
                return actionFactory.getShowUserAlbumsAction()
                        .execute(reader, userLogged);

            case SHOW_PHOTOS:
                return actionFactory.getShowPhotosAction()
                        .execute(reader, userLogged);

            case ADD_PHOTO:
                return actionFactory.getAddPhotoAction()
                        .execute(reader, userLogged);

            case DELETE_PHOTO:
                return actionFactory.getDeletePhotoAction()
                        .execute(reader, userLogged);

            case LIKE_PHOTO:
                return actionFactory.getLikePhotoAction()
                        .execute(reader, userLogged);

            case UNLIKE_PHOTO:
                return actionFactory.getUnlikePhotoAction()
                        .execute(reader, userLogged);

            case ADD_FRIEND:
                return actionFactory.getAddFriendAction()
                        .execute(reader, userLogged);

            case DELETE_FRIEND:
                return actionFactory.getDeleteFriendAction()
                        .execute(reader, userLogged);

            case SHOW_FRIENDS:
                return actionFactory.getShowFriendsAction()
                        .execute(userLogged);

            case DELETE_ACCOUNT:
                return actionFactory.getDeleteAccountAction()
                        .execute(reader, userLogged);

            case LOGOUT:
                return actionFactory.getLogoutAction()
                        .execute(reader, userLogged);

            default:
                return MenuResult.CONTINUE;
        }
    }
}