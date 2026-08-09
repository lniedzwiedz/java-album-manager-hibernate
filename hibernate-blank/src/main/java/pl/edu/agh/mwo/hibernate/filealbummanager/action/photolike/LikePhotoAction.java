package pl.edu.agh.mwo.hibernate.filealbummanager.action.photolike;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.photolike.PhotoLikeHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Photo;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.photolike.PhotoLikeStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.FriendService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoLikeService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoLikeMessages;

import java.io.IOException;

public class LikePhotoAction extends PhotoLikeBaseAction {

    private final PhotoLikeService photoLikeService;
    private final PhotoLikeHandler photoLikeHandler;

    public LikePhotoAction(UserService userService, FriendService friendService,
                           AlbumService albumService, PhotoService photoService,
                           PhotoLikeService photoLikeService, PhotoLikeHandler photoLikeHandler) {

        super(userService, friendService, albumService, photoService);
        this.photoLikeService = photoLikeService;
        this.photoLikeHandler = photoLikeHandler;
    }

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {
        if (userLogged == null)
            return MenuResult.CONTINUE;

        User ownerUser = getOwnerUser(reader);
        if (ownerUser == null)
            return MenuResult.CONTINUE;

        if (!areFriends(userLogged, ownerUser)) {
            System.out.println(PhotoLikeMessages.NOT_FRIEND_PHOTO_OWNER);
            return MenuResult.CONTINUE;
        }

        Album album = getAlbum(reader, ownerUser, PhotoLikeMessages.ALBUM_NAME_LIKE);
        if (album == null)
            return MenuResult.CONTINUE;

        Photo photo = getPhoto(reader, album, PhotoLikeMessages.ADD_LIKE_PHOTO_NAME);
        if (photo == null)
            return MenuResult.CONTINUE;

        PhotoLikeStatus status = photoLikeService.checkPhotoLikeStatus(userLogged, ownerUser, album, photo);
        return photoLikeHandler.handleLike(status, photo, userLogged);
    }
}