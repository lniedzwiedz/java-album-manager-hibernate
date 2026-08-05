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
import pl.edu.agh.mwo.hibernate.filealbummanager.action.photo.LikePhotoAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.photo.ShowPhotosAction;
import pl.edu.agh.mwo.hibernate.filealbummanager.action.photo.UnlikePhotoAction;

import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.FriendService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserService;

public class ActionFactory {

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

    public ActionFactory(AlbumService albumService, PhotoService photoService, FriendService friendService, UserService userService) {

        this.addAlbumAction = new AddAlbumAction(albumService);
        this.deleteAlbumAction = new DeleteAlbumAction(albumService);
        this.showMyAlbumsAction = new ShowMyAlbumsAction(albumService);
        this.showUserAlbumsAction = new ShowUserAlbumsAction(albumService, userService);

        this.showPhotosAction = new ShowPhotosAction(albumService, photoService);
        this.addPhotoAction = new AddPhotoAction(albumService, photoService);
        this.deletePhotoAction = new DeletePhotoAction(photoService);
        this.likePhotoAction = new LikePhotoAction(photoService);

        this.unlikePhotoAction = new UnlikePhotoAction(photoService);
        this.addFriendAction = new AddFriendAction(userService, friendService);
        this.deleteFriendAction = new DeleteFriendAction(userService, friendService);
        this.showFriendsAction = new ShowFriendsAction(friendService);

        this.deleteAccountAction = new DeleteAccountAction(userService);
        this.logoutAction = new LogoutAction();
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

    public LikePhotoAction getLikePhotoAction() {
        return likePhotoAction;
    }

    public UnlikePhotoAction getUnlikePhotoAction() {
        return unlikePhotoAction;
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