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

import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.account.DeleteAccountHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.account.LogoutHandler;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.album.AddAlbumHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.album.DeleteAlbumHandler;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.friend.AddFriendHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.friend.DeleteFriendHandler;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.photo.AddPhotoHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.photo.DeletePhotoHandler;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.photolike.AddPhotoLikeHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.photolike.DeletePhotoLikeHandler;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.photo.AddPhotoAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.photo.DeletePhotoAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.photo.ShowPhotosAction;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.photolike.AddPhotoLikeAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.photolike.DeletePhotoLikeAction;

import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.FriendService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoLikeService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserService;

import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsolePrinter;

public class ActionFactory {

    private final DeleteAccountAction deleteAccountAction;
    private final LogoutAction logoutAction;

    private final AddFriendAction addFriendAction;
    private final DeleteFriendAction deleteFriendAction;
    private final ShowFriendsAction showFriendsAction;

    private final AddAlbumAction addAlbumAction;
    private final DeleteAlbumAction deleteAlbumAction;
    private final ShowMyAlbumsAction showMyAlbumsAction;
    private final ShowUserAlbumsAction showUserAlbumsAction;

    private final AddPhotoAction addPhotoAction;
    private final DeletePhotoAction deletePhotoAction;
    private final ShowPhotosAction showPhotosAction;

    private final AddPhotoLikeAction addPhotoLikeAction;
    private final DeletePhotoLikeAction deletePhotoLikeAction;


    public ActionFactory(
            UserService userService,
            FriendService friendService,
            AlbumService albumService,
            PhotoService photoService,
            PhotoLikeService photoLikeService,
            ConsolePrinter consolePrinter) {

        DeleteAccountHandler deleteAccountHandler = new DeleteAccountHandler();
        LogoutHandler logoutHandler = new LogoutHandler();

        AddFriendHandler addFriendHandler = new AddFriendHandler();
        DeleteFriendHandler deleteFriendHandler = new DeleteFriendHandler();

        AddAlbumHandler addAlbumHandler = new AddAlbumHandler();
        DeleteAlbumHandler deleteAlbumHandler = new DeleteAlbumHandler();

        AddPhotoHandler addPhotoHandler = new AddPhotoHandler();
        DeletePhotoHandler deletePhotoHandler = new DeletePhotoHandler();

        AddPhotoLikeHandler addPhotoLikeHandler = new AddPhotoLikeHandler();
        DeletePhotoLikeHandler deletePhotoLikeHandler = new DeletePhotoLikeHandler();

        this.deleteAccountAction =
                new DeleteAccountAction(
                        userService,
                        deleteAccountHandler
                );

        this.logoutAction =
                new LogoutAction(
                        logoutHandler
                );

        this.addFriendAction =
                new AddFriendAction(
                        friendService,
                        addFriendHandler
                );

        this.deleteFriendAction =
                new DeleteFriendAction(
                        friendService,
                        deleteFriendHandler
                );

        this.showFriendsAction =
                new ShowFriendsAction(
                        friendService,
                        consolePrinter
                );

        this.addAlbumAction =
                new AddAlbumAction(
                        albumService,
                        addAlbumHandler
                );

        this.deleteAlbumAction =
                new DeleteAlbumAction(
                        albumService,
                        deleteAlbumHandler
                );

        this.showMyAlbumsAction =
                new ShowMyAlbumsAction(
                        albumService,
                        consolePrinter
                );

        this.showUserAlbumsAction =
                new ShowUserAlbumsAction(
                        userService,
                        friendService,
                        albumService,
                        consolePrinter
                );

        this.showPhotosAction =
                new ShowPhotosAction(
                        albumService,
                        photoService,
                        photoLikeService
                );

        this.addPhotoAction =
                new AddPhotoAction(
                        albumService,
                        photoService,
                        addPhotoHandler
                );

        this.deletePhotoAction =
                new DeletePhotoAction(
                        albumService,
                        photoService,
                        deletePhotoHandler
                );

        this.addPhotoLikeAction =
                new AddPhotoLikeAction(
                        userService,
                        albumService,
                        photoService,
                        photoLikeService,
                        addPhotoLikeHandler
                );

        this.deletePhotoLikeAction =
                new DeletePhotoLikeAction(
                        userService,
                        albumService,
                        photoService,
                        photoLikeService,
                        deletePhotoLikeHandler
                );
    }

    public AddAlbumAction getAddAlbumAction() {
        return addAlbumAction;
    }

    public DeleteAlbumAction getDeleteAlbumAction() {
        return deleteAlbumAction;
    }

    public ShowMyAlbumsAction getShowMyAlbumsAction() {
        return showMyAlbumsAction;
    }

    public ShowUserAlbumsAction getShowUserAlbumsAction() {
        return showUserAlbumsAction;
    }

    public ShowPhotosAction getShowPhotosAction() {
        return showPhotosAction;
    }

    public AddPhotoAction getAddPhotoAction() {
        return addPhotoAction;
    }

    public DeletePhotoAction getDeletePhotoAction() {
        return deletePhotoAction;
    }

    public AddPhotoLikeAction getAddPhotoLikeAction() {
        return addPhotoLikeAction;
    }

    public DeletePhotoLikeAction getDeletePhotoLikeAction() {
        return deletePhotoLikeAction;
    }

    public AddFriendAction getAddFriendAction() {
        return addFriendAction;
    }

    public DeleteFriendAction getDeleteFriendAction() {
        return deleteFriendAction;
    }

    public ShowFriendsAction getShowFriendsAction() {
        return showFriendsAction;
    }

    public DeleteAccountAction getDeleteAccountAction() {
        return deleteAccountAction;
    }

    public LogoutAction getLogoutAction() {
        return logoutAction;
    }
}