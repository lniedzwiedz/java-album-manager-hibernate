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

    private final AddAlbumAction addAlbumAction;
    private final DeleteAlbumAction deleteAlbumAction;
    private final ShowMyAlbumsAction showMyAlbumsAction;
    private final ShowUserAlbumsAction showUserAlbumsAction;

    private final ShowPhotosAction showPhotosAction;
    private final AddPhotoAction addPhotoAction;
    private final DeletePhotoAction deletePhotoAction;

    private final AddAlbumHandler addAlbumHandler;
    private final DeleteAlbumHandler deleteAlbumHandler;

    private final AddPhotoHandler addPhotoHandler;
    private final DeletePhotoHandler deletePhotoHandler;

    private final AddPhotoLikeHandler addPhotoLikeHandler;
    private final DeletePhotoLikeHandler deletePhotoLikeHandler;

    private final AddFriendHandler addFriendHandler;
    private final DeleteFriendHandler deleteFriendHandler;

    private final AddPhotoLikeAction addPhotoLikeAction;
    private final DeletePhotoLikeAction deletePhotoLikeAction;

    private final AddFriendAction addFriendAction;
    private final DeleteFriendAction deleteFriendAction;
    private final ShowFriendsAction showFriendsAction;

    private final DeleteAccountHandler deleteAccountHandler;
    private final DeleteAccountAction deleteAccountAction;

    private final LogoutHandler logoutHandler;
    private final LogoutAction logoutAction;

    public ActionFactory(
            AlbumService albumService,
            PhotoService photoService,
            PhotoLikeService photoLikeService,
            FriendService friendService,
            UserService userService,
            ConsolePrinter consolePrinter) {

        this.addAlbumHandler =
                new AddAlbumHandler();

        this.deleteAlbumHandler =
                new DeleteAlbumHandler();

        this.addPhotoHandler =
                new AddPhotoHandler();

        this.deletePhotoHandler =
                new DeletePhotoHandler();

        this.addPhotoLikeHandler =
                new AddPhotoLikeHandler();

        this.deletePhotoLikeHandler =
                new DeletePhotoLikeHandler();

        this.addFriendHandler =
                new AddFriendHandler();

        this.deleteFriendHandler =
                new DeleteFriendHandler();

        this.deleteAccountHandler =
                new DeleteAccountHandler();

        this.logoutHandler =
                new LogoutHandler();

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
                        albumService,
                        userService,
                        friendService,
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
                        photoLikeService,
                        addPhotoLikeHandler,
                        userService,
                        albumService,
                        photoService
                );

        this.deletePhotoLikeAction =
                new DeletePhotoLikeAction(
                        photoLikeService,
                        deletePhotoLikeHandler,
                        userService,
                        albumService,
                        photoService
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

        this.deleteAccountAction =
                new DeleteAccountAction(
                        userService,
                        deleteAccountHandler
                );

        this.logoutAction =
                new LogoutAction(
                        logoutHandler
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