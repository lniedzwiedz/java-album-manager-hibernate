package pl.edu.agh.mwo.hibernate.filealbummanager.action.photolike;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Photo;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.FriendService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.album.AlbumMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.application.ApplicationMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.friend.FriendMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoMessages;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoLikeMessages;

import java.io.IOException;

public abstract class PhotoLikeBaseAction {

    protected final UserService userService;
    protected final FriendService friendService;
    protected final AlbumService albumService;
    protected final PhotoService photoService;

    protected PhotoLikeBaseAction(UserService userService, FriendService friendService,
                                  AlbumService albumService, PhotoService photoService) {
        this.userService = userService;
        this.friendService = friendService;
        this.albumService = albumService;
        this.photoService = photoService;
    }

    protected User getOwnerUser(ConsoleReader reader) throws IOException {
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

    protected boolean areFriends(User userLogged, User ownerUser) {
        return userLogged.getId() == ownerUser.getId() ||
                friendService.areFriends(userLogged, ownerUser);
    }

    protected Album getAlbum(ConsoleReader reader, User ownerUser, String message) throws IOException {
        System.out.println(message);

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

    protected Photo getPhoto(ConsoleReader reader, Album album, String message) throws IOException {
        System.out.println(message);

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
}