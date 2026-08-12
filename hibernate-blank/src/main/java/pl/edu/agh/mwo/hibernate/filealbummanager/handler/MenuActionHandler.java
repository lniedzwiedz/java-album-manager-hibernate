package pl.edu.agh.mwo.hibernate.filealbummanager.handler;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.ActionFactory;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.menu.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.account.AccountDeleteResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.status.account.AccountDeleteStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.account.LogoutResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.status.account.LogoutStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.option.MenuOption;

import java.io.IOException;

public class MenuActionHandler {

    private final ActionFactory actionFactory;

    public MenuActionHandler(ActionFactory actionFactory) {
        this.actionFactory = actionFactory;
    }

    public MenuResult execute(MenuOption menuOption, ConsoleReader reader, User userLogged) throws IOException {
        if (menuOption == null)
            return MenuResult.CONTINUE;

        switch (menuOption) {
            case ADD_ALBUM:
                return actionFactory.getAddAlbumAction().execute(reader, userLogged);

            case DELETE_ALBUM:
                return actionFactory.getDeleteAlbumAction().execute(reader, userLogged);

            case SHOW_MY_ALBUMS:
                return actionFactory.getShowMyAlbumsAction().execute(userLogged);

            case SHOW_USER_ALBUMS:
                return actionFactory.getShowUserAlbumsAction().execute(reader, userLogged);

            case SHOW_PHOTOS:
                return actionFactory.getShowPhotosAction().execute(reader, userLogged);

            case ADD_PHOTO:
                return actionFactory.getAddPhotoAction().execute(reader, userLogged);

            case DELETE_PHOTO:
                return actionFactory.getDeletePhotoAction().execute(reader, userLogged);

            case LIKE_PHOTO:
                return actionFactory.getAddPhotoLikeAction().execute(reader, userLogged);

            case UNLIKE_PHOTO:
                return actionFactory.getDeletePhotoLikeAction().execute(reader, userLogged);

            case ADD_FRIEND:
                return actionFactory.getAddFriendAction().execute(reader, userLogged);

            case DELETE_FRIEND:
                return actionFactory.getDeleteFriendAction().execute(reader, userLogged);

            case SHOW_FRIENDS:
                return actionFactory.getShowFriendsAction().execute(userLogged);

            case DELETE_ACCOUNT:
                AccountDeleteResult deleteResult = actionFactory.getDeleteAccountAction().execute(reader, userLogged);
                if (deleteResult.getStatus() == AccountDeleteStatus.ACCOUNT_DELETED) return MenuResult.EXIT;
                return MenuResult.CONTINUE;

            case LOGOUT:
                LogoutResult logoutResult = actionFactory.getLogoutAction().execute(reader, userLogged);
                if (logoutResult.getStatus() == LogoutStatus.LOGGED_OUT) return MenuResult.EXIT;
                return MenuResult.CONTINUE;

            default:
                return MenuResult.CONTINUE;
        }
    }
}