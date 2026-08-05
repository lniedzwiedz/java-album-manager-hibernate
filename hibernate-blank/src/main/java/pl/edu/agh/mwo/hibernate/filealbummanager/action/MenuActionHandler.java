package pl.edu.agh.mwo.hibernate.filealbummanager.action;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.account.DeleteAccountAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.account.LogoutAction;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.album.AddAlbumAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.album.DeleteAlbumAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.album.ShowMyAlbumsAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.album.ShowUserAlbumsAction;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.friend.AddFriendAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.friend.DeleteFriendAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.friend.ShowFriendsAction;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.photo.AddPhotoAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.photo.DeletePhotoAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.photolike.LikePhotoAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.photo.ShowPhotosAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.photolike.UnlikePhotoAction;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.application.MenuOption;

import java.io.BufferedReader;
import java.io.IOException;

public class MenuActionHandler {

    private final AddAlbumAction addAlbumAction;
    private final DeleteAlbumAction deleteAlbumAction;
    private final ShowMyAlbumsAction showMyAlbumsAction;
    private final ShowUserAlbumsAction showUserAlbumsAction;

    private final ShowPhotosAction showPhotosAction;
    private final AddPhotoAction addPhotoAction;
    private final DeletePhotoAction deletePhotoAction;
    private final LikePhotoAction likePhotoAction;
    private final UnlikePhotoAction unlikePhotoAction;

    private final AddFriendAction addFriendAction;
    private final DeleteFriendAction deleteFriendAction;
    private final ShowFriendsAction showFriendsAction;

    private final DeleteAccountAction deleteAccountAction;
    private final LogoutAction logoutAction;

    public MenuActionHandler(ActionFactory actionFactory) {
        this.addAlbumAction = actionFactory.getAddAlbumAction();
        this.deleteAlbumAction = actionFactory.getDeleteAlbumAction();
        this.showMyAlbumsAction = actionFactory.getShowMyAlbumsAction();
        this.showUserAlbumsAction = actionFactory.getShowUserAlbumsAction();
        this.showPhotosAction = actionFactory.getShowPhotosAction();
        this.addPhotoAction = actionFactory.getAddPhotoAction();
        this.deletePhotoAction = actionFactory.getDeletePhotoAction();
        this.likePhotoAction = actionFactory.getLikePhotoAction();
        this.unlikePhotoAction = actionFactory.getUnlikePhotoAction();
        this.addFriendAction = actionFactory.getAddFriendAction();
        this.deleteFriendAction = actionFactory.getDeleteFriendAction();
        this.showFriendsAction = actionFactory.getShowFriendsAction();
        this.deleteAccountAction = actionFactory.getDeleteAccountAction();
        this.logoutAction = actionFactory.getLogoutAction();
    }

    public boolean execute(MenuOption menuOption, BufferedReader br, User userLogged) throws IOException {

        switch (menuOption) {

            case ADD_ALBUM:
                addAlbumAction.execute(br, userLogged);
                break;

            case DELETE_ALBUM:
                deleteAlbumAction.execute(br, userLogged);
                break;

            case SHOW_MY_ALBUMS:
                showMyAlbumsAction.execute(userLogged);
                break;

            case SHOW_USER_ALBUMS:
                showUserAlbumsAction.execute(br);
                break;

            case SHOW_PHOTOS:
                showPhotosAction.execute(br, userLogged);
                break;

            case ADD_PHOTO:
                addPhotoAction.execute(br, userLogged);
                break;

            case DELETE_PHOTO:
                deletePhotoAction.execute(br, userLogged);
                break;

            case LIKE_PHOTO:
                likePhotoAction.execute(br, userLogged);
                break;

            case UNLIKE_PHOTO:
                unlikePhotoAction.execute(br, userLogged);
                break;

            case ADD_FRIEND:
                addFriendAction.execute(br, userLogged);
                break;

            case DELETE_FRIEND:
                deleteFriendAction.execute(br, userLogged);
                break;

            case SHOW_FRIENDS:
                showFriendsAction.execute(userLogged);
                break;

            case DELETE_ACCOUNT:
                return deleteAccountAction.execute(br, userLogged);

            case LOGOUT:
                return logoutAction.execute(br, userLogged);

            default:
                return false;
        }
        return false;
    }
}