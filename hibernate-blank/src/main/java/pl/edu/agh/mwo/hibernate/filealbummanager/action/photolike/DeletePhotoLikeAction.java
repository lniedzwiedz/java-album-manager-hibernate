package pl.edu.agh.mwo.hibernate.filealbummanager.action.photolike;

import pl.edu.agh.mwo.hibernate.filealbummanager.action.handler.photolike.DeletePhotoLikeHandler;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Photo;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.MenuResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.photolike.PhotoLikeDeleteResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.AlbumService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoLikeService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.PhotoService;
import pl.edu.agh.mwo.hibernate.filealbummanager.service.UserService;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.console.ConsoleReader;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.photo.PhotoLikeMessages;

import java.io.IOException;

public class DeletePhotoLikeAction {

    private final UserService userService;
    private final AlbumService albumService;
    private final PhotoService photoService;
    private final PhotoLikeService photoLikeService;
    private final DeletePhotoLikeHandler deletePhotoLikeHandler;

    public DeletePhotoLikeAction(
            UserService userService,
            AlbumService albumService,
            PhotoService photoService,
            PhotoLikeService photoLikeService,
            DeletePhotoLikeHandler deletePhotoLikeHandler
    ) {
        this.userService = userService;
        this.albumService = albumService;
        this.photoService = photoService;
        this.photoLikeService = photoLikeService;
        this.deletePhotoLikeHandler = deletePhotoLikeHandler;
    }

    public MenuResult execute(ConsoleReader reader, User userLogged) throws IOException {
        if (userLogged == null || userLogged.getId() <= 0) {
            System.out.println(PhotoLikeMessages.LOGGED_USER_NOT_FOUND);
            return MenuResult.CONTINUE;
        }

        System.out.println(PhotoLikeMessages.PHOTO_OWNER_USERNAME);
        String friendName = reader.readLine();

        if (friendName == null || friendName.isBlank()) {
            System.out.println(PhotoLikeMessages.FRIEND_DATA_NOT_FOUND);
            return MenuResult.CONTINUE;
        }

        User friend = userService.getUser(friendName);
        if (friend == null || friend.getId() <= 0) {
            System.out.println(PhotoLikeMessages.USER_NOT_FOUND);
            return MenuResult.CONTINUE;
        }

        if (userLogged.getId() == friend.getId()) {
            System.out.println(PhotoLikeMessages.NOT_FRIENDS);
            return MenuResult.CONTINUE;
        }

        boolean areFriends = userLogged.getUsers().contains(friend) ||
                friend.getUsers().contains(userLogged);

        if (!areFriends) {
            System.out.println(PhotoLikeMessages.NOT_FRIENDS);
            return MenuResult.CONTINUE;
        }

        System.out.println(PhotoLikeMessages.ALBUM_NAME_LIKE);
        String albumName = reader.readLine();

        if (albumName == null || albumName.isBlank()) {
            System.out.println(PhotoLikeMessages.ALBUM_DATA_NOT_FOUND);
            return MenuResult.CONTINUE;
        }

        Album album = albumService.getAlbum(albumName, friend.getId());
        if (album == null || album.getId() <= 0) {
            System.out.println(PhotoLikeMessages.ALBUM_NOT_FOUND);
            return MenuResult.CONTINUE;
        }

        System.out.println(PhotoLikeMessages.REMOVE_PHOTO_LIKE_NAME);
        String photoName = reader.readLine();

        if (photoName == null || photoName.isBlank()) {
            System.out.println(PhotoLikeMessages.PHOTO_DATA_NOT_FOUND);
            return MenuResult.CONTINUE;
        }

        Photo photo = photoService.getPhoto(photoName, album.getId());
        if (photo == null || photo.getId() <= 0) {
            System.out.println(PhotoLikeMessages.PHOTO_NOT_FOUND);
            return MenuResult.CONTINUE;
        }

        PhotoLikeDeleteResult result = photoLikeService.deletePhotoLike(userLogged, friend, album, photo);
        return deletePhotoLikeHandler.handle(result);
    }
}