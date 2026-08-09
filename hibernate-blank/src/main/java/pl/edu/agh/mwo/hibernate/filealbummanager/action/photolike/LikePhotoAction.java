package pl.edu.agh.mwo.hibernate.filealbummanager.action.photolike;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Photo;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.*;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.album.AlbumMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.friend.FriendMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoLikeMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.PhotoLikeStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoMessages;

import java.io.IOException;

public class LikePhotoAction {

    private final UserService userService;
    private final FriendService friendService;
    private final AlbumService albumService;
    private final PhotoService photoService;
    private final PhotoLikeService photoLikeService;

    public LikePhotoAction(UserService userService, FriendService friendService, AlbumService albumService, PhotoService photoService, PhotoLikeService photoLikeService) {
        this.albumService = albumService;
        this.friendService = friendService;
        this.photoService = photoService;
        this.photoLikeService = photoLikeService;
        this.userService = userService;
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

        Album album = getAlbum(reader, ownerUser);
        if (album == null)
            return MenuResult.CONTINUE;

        Photo photo = getPhoto(reader, album);
        if (photo == null)
            return MenuResult.CONTINUE;

        PhotoLikeStatus status = photoLikeService.checkPhotoLikeStatus(userLogged, ownerUser, album, photo);

        return handleLikeStatus(status, photo, userLogged);
    }

    private User getOwnerUser(ConsoleReader reader) throws IOException {

        System.out.println(PhotoLikeMessages.PHOTO_OWNER_USERNAME);

        String userName = reader.readLine();
        if (userName == null || userName.isBlank()) {
            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
            return null;
        }

        User ownerUser = userService.getUser(userName);
        if (ownerUser == null) {
            System.out.println(FriendMessages.NOT_FRIEND);
            return null;
        }
        return ownerUser;
    }

    private boolean areFriends(User userLogged, User ownerUser) {
        return userLogged.getId() == ownerUser.getId() || friendService.areFriends(userLogged, ownerUser);
    }

    private Album getAlbum(ConsoleReader reader, User ownerUser) throws IOException {

        System.out.println(PhotoLikeMessages.ALBUM_NAME_LIKE);

        String albumName = reader.readLine();
        if (albumName == null || albumName.isBlank()) {
            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
            return null;
        }

        Album album = albumService.getAlbum(albumName, ownerUser.getId());

        if (album == null) {
            System.out.println(AlbumMessages.ALBUM_DOES_NOT_EXIST);
            return null;
        }

        return album;
    }

    private Photo getPhoto(ConsoleReader reader, Album album) throws IOException {

        System.out.println(PhotoLikeMessages.ADD_LIKE_PHOTO_NAME);

        String photoName = reader.readLine();
        if (photoName == null || photoName.isBlank()) {
            System.out.println(ApplicationMessages.INVALID_INPUT_E3);
            return null;
        }

        Photo photo = photoService.getPhoto(photoName, album.getId());
        if (photo == null) {
            System.out.println(PhotoMessages.PHOTO_NOT_IN_ALBUM);
            return null;
        }
        return photo;
    }

    private MenuResult handleLikeStatus(PhotoLikeStatus status, Photo photo, User userLogged) {

        if (status == null) {
            System.out.println(PhotoLikeMessages.PHOTO_LIKE_ERROR);
            return MenuResult.CONTINUE;
        }

        switch (status) {

            case NEVER_LIKED:
                boolean added = photoLikeService.addPhotoLike(photo, userLogged);

                if (added) {
                    System.out.println(PhotoLikeMessages.PHOTO_LIKE_ADDED);
                } else {
                    System.out.println(PhotoLikeMessages.PHOTO_LIKE_ERROR);
                }
                break;

            case ALREADY_LIKED:
                System.out.println(String.format(PhotoLikeMessages.ALREADY_LIKE_PHOTO, userLogged.getName()));
                break;

            case PHOTO_NOT_IN_ALBUM:
                System.out.println(PhotoMessages.PHOTO_NOT_IN_ALBUM);
                break;

            case ALBUM_DOES_NOT_EXIST:
                System.out.println(AlbumMessages.ALBUM_DOES_NOT_EXIST);
                break;

            case NOT_FRIEND_PHOTO_OWNER:
                System.out.println(PhotoLikeMessages.NOT_FRIEND_PHOTO_OWNER);
                break;

            case PHOTO_LIKE_ERROR:
                System.out.println(PhotoLikeMessages.PHOTO_LIKE_ERROR);
                break;

            default:
                System.out.println(PhotoLikeMessages.PHOTO_LIKE_ERROR);
                break;
        }
        return MenuResult.CONTINUE;
    }
}