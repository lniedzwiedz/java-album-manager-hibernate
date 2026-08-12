package pl.edu.agh.mwo.hibernate.filealbummanager.service;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Photo;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.PhotoLikeRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.status.photolike.AddPhotoLikeStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.status.photolike.DeletePhotoLikeStatus;

public class PhotoLikeService {

    private final UserService userService;
    private final AlbumService albumService;
    private final PhotoService photoService;
    private final PhotoLikeRepository photoLikeRepository;

    public PhotoLikeService(UserService userService, AlbumService albumService,
                            PhotoService photoService, PhotoLikeRepository photoLikeRepository) {
        this.userService = userService;
        this.albumService = albumService;
        this.photoService = photoService;
        this.photoLikeRepository = photoLikeRepository;
    }

    public AddPhotoLikeStatus addPhotoLike(User user, String friendName, String albumName, String photoName) {
        if (user == null || user.getId() <= 0)
            return AddPhotoLikeStatus.LOGGED_USER_NOT_FOUND;

        if (friendName == null || friendName.isBlank())
            return AddPhotoLikeStatus.FRIEND_DATA_NOT_FOUND;

        User friend = userService.getUser(friendName);
        if (friend == null || friend.getId() <= 0)
            return AddPhotoLikeStatus.USER_NOT_FOUND;

        if (user.getId() == friend.getId())
            return AddPhotoLikeStatus.NOT_FRIENDS;

        boolean areFriends = user.getUsers().contains(friend) ||
                friend.getUsers().contains(user);

        if (!areFriends)
            return AddPhotoLikeStatus.NOT_FRIENDS;

        if (albumName == null || albumName.isBlank())
            return AddPhotoLikeStatus.ALBUM_DATA_NOT_FOUND;

        Album album = albumService.getAlbum(albumName, friend.getId());
        if (album == null || album.getId() <= 0)
            return AddPhotoLikeStatus.ALBUM_NOT_FOUND;

        if (album.getUserId() != friend.getId())
            return AddPhotoLikeStatus.ALBUM_NOT_OWNED_BY_USER;

        if (photoName == null || photoName.isBlank())
            return AddPhotoLikeStatus.PHOTO_DATA_NOT_FOUND;

        Photo photo = photoService.getPhoto(photoName, album.getId());
        if (photo == null || photo.getId() <= 0)
            return AddPhotoLikeStatus.PHOTO_NOT_FOUND;

        if (photo.getAlbumId() != album.getId())
            return AddPhotoLikeStatus.PHOTO_NOT_IN_ALBUM;

        if (photo.getUsers().contains(user))
            return AddPhotoLikeStatus.PHOTO_ALREADY_LIKED;

        photoLikeRepository.addPhotoLike(user, photo);
        return AddPhotoLikeStatus.PHOTO_LIKE_ADDED;
    }

    public DeletePhotoLikeStatus deletePhotoLike(User user, String friendName, String albumName, String photoName) {
        if (user == null || user.getId() <= 0)
            return DeletePhotoLikeStatus.LOGGED_USER_NOT_FOUND;

        if (friendName == null || friendName.isBlank())
            return DeletePhotoLikeStatus.FRIEND_DATA_NOT_FOUND;

        User friend = userService.getUser(friendName);
        if (friend == null || friend.getId() <= 0)
            return DeletePhotoLikeStatus.USER_NOT_FOUND;

        if (user.getId() == friend.getId())
            return DeletePhotoLikeStatus.NOT_FRIENDS;

        boolean areFriends = user.getUsers().contains(friend) ||
                friend.getUsers().contains(user);

        if (!areFriends)
            return DeletePhotoLikeStatus.NOT_FRIENDS;

        if (albumName == null || albumName.isBlank())
            return DeletePhotoLikeStatus.ALBUM_DATA_NOT_FOUND;

        Album album = albumService.getAlbum(albumName, friend.getId());
        if (album == null || album.getId() <= 0)
            return DeletePhotoLikeStatus.ALBUM_NOT_FOUND;

        if (album.getUserId() != friend.getId())
            return DeletePhotoLikeStatus.ALBUM_NOT_OWNED_BY_USER;

        if (photoName == null || photoName.isBlank())
            return DeletePhotoLikeStatus.PHOTO_DATA_NOT_FOUND;

        Photo photo = photoService.getPhoto(photoName, album.getId());
        if (photo == null || photo.getId() <= 0)
            return DeletePhotoLikeStatus.PHOTO_NOT_FOUND;

        if (photo.getAlbumId() != album.getId())
            return DeletePhotoLikeStatus.PHOTO_NOT_IN_ALBUM;

        if (!photo.getUsers().contains(user))
            return DeletePhotoLikeStatus.PHOTO_NOT_LIKED;

        photoLikeRepository.deletePhotoLike(user, photo);
        return DeletePhotoLikeStatus.PHOTO_LIKE_DELETED;
    }

    public int countPhotoLikes(Photo photo) {
        if (photo == null || photo.getId() <= 0)
            return 0;

        return photoLikeRepository.countPhotoLikes(photo);
    }
}