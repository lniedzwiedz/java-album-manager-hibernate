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
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.FriendManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoManagerService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserManagerService;

public class ActionFactory {

    public final AddAlbumAction addAlbumAction;
    public final DeleteAlbumAction deleteAlbumAction;
    public final ShowMyAlbumsAction showMyAlbumsAction;
    public final ShowUserAlbumsAction showUserAlbumsAction;

    public final ShowPhotosAction showPhotosAction;
    public final AddPhotoAction addPhotoAction;
    public final DeletePhotoAction deletePhotoAction;
    public final LikePhotoAction likePhotoAction;
    public final UnlikePhotoAction unlikePhotoAction;

    public final AddFriendAction addFriendAction;
    public final DeleteFriendAction deleteFriendAction;
    public final ShowFriendsAction showFriendsAction;

    public final DeleteAccountAction deleteAccountAction;
    public final LogoutAction logoutAction;

    public ActionFactory(AlbumManagerService albumManager, PhotoManagerService photoManager, FriendManagerService friendManager, UserManagerService userManager) {
        addAlbumAction = new AddAlbumAction(albumManager);
        deleteAlbumAction = new DeleteAlbumAction(albumManager);
        showMyAlbumsAction = new ShowMyAlbumsAction(albumManager);
        showUserAlbumsAction = new ShowUserAlbumsAction(albumManager, userManager);
        showPhotosAction = new ShowPhotosAction(albumManager, photoManager);
        addPhotoAction = new AddPhotoAction(albumManager, photoManager);
        deletePhotoAction = new DeletePhotoAction(albumManager, photoManager);
        likePhotoAction = new LikePhotoAction(photoManager);
        unlikePhotoAction = new UnlikePhotoAction(photoManager);
        addFriendAction = new AddFriendAction(userManager, friendManager);
        deleteFriendAction = new DeleteFriendAction(userManager, friendManager);
        showFriendsAction = new ShowFriendsAction(friendManager);
        deleteAccountAction = new DeleteAccountAction(userManager);
        logoutAction = new LogoutAction();
    }
}