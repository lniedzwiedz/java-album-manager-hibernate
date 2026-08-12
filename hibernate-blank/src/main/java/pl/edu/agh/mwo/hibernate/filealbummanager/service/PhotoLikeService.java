package pl.edu.agh.mwo.hibernate.filealbummanager.service;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Photo;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.PhotoLikeRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.photolike.PhotoLikeAddStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.photolike.PhotoLikeDeleteStatus;

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

    public PhotoLikeAddStatus addPhotoLike(User user, String friendName, String albumName, String photoName) {
        if (user == null || user.getId() <= 0)
            return PhotoLikeAddStatus.LOGGED_USER_NOT_FOUND;

        if (friendName == null || friendName.isBlank())
            return PhotoLikeAddStatus.FRIEND_DATA_NOT_FOUND;

        User friend = userService.getUser(friendName);
        if (friend == null || friend.getId() <= 0)
            return PhotoLikeAddStatus.USER_NOT_FOUND;

        if (user.getId() == friend.getId())
            return PhotoLikeAddStatus.NOT_FRIENDS;

        boolean areFriends = user.getUsers().contains(friend) ||
                friend.getUsers().contains(user);

        if (!areFriends)
            return PhotoLikeAddStatus.NOT_FRIENDS;

        if (albumName == null || albumName.isBlank())
            return PhotoLikeAddStatus.ALBUM_DATA_NOT_FOUND;

        Album album = albumService.getAlbum(albumName, friend.getId());
        if (album == null || album.getId() <= 0)
            return PhotoLikeAddStatus.ALBUM_NOT_FOUND;

        if (album.getUserId() != friend.getId())
            return PhotoLikeAddStatus.ALBUM_NOT_OWNED_BY_USER;

        if (photoName == null || photoName.isBlank())
            return PhotoLikeAddStatus.PHOTO_DATA_NOT_FOUND;

        Photo photo = photoService.getPhoto(photoName, album.getId());
        if (photo == null || photo.getId() <= 0)
            return PhotoLikeAddStatus.PHOTO_NOT_FOUND;

        if (photo.getAlbumId() != album.getId())
            return PhotoLikeAddStatus.PHOTO_NOT_IN_ALBUM;

        if (photo.getUsers().contains(user))
            return PhotoLikeAddStatus.PHOTO_ALREADY_LIKED;

        photoLikeRepository.addPhotoLike(user, photo);
        return PhotoLikeAddStatus.PHOTO_LIKE_ADDED;
    }

    public PhotoLikeDeleteStatus deletePhotoLike(User user, String friendName, String albumName, String photoName) {
        if (user == null || user.getId() <= 0)
            return PhotoLikeDeleteStatus.LOGGED_USER_NOT_FOUND;

        if (friendName == null || friendName.isBlank())
            return PhotoLikeDeleteStatus.FRIEND_DATA_NOT_FOUND;

        User friend = userService.getUser(friendName);
        if (friend == null || friend.getId() <= 0)
            return PhotoLikeDeleteStatus.USER_NOT_FOUND;

        if (user.getId() == friend.getId())
            return PhotoLikeDeleteStatus.NOT_FRIENDS;

        boolean areFriends = user.getUsers().contains(friend) ||
                friend.getUsers().contains(user);

        if (!areFriends)
            return PhotoLikeDeleteStatus.NOT_FRIENDS;

        if (albumName == null || albumName.isBlank())
            return PhotoLikeDeleteStatus.ALBUM_DATA_NOT_FOUND;

        Album album = albumService.getAlbum(albumName, friend.getId());
        if (album == null || album.getId() <= 0)
            return PhotoLikeDeleteStatus.ALBUM_NOT_FOUND;

        if (album.getUserId() != friend.getId())
            return PhotoLikeDeleteStatus.ALBUM_NOT_OWNED_BY_USER;

        if (photoName == null || photoName.isBlank())
            return PhotoLikeDeleteStatus.PHOTO_DATA_NOT_FOUND;

        Photo photo = photoService.getPhoto(photoName, album.getId());
        if (photo == null || photo.getId() <= 0)
            return PhotoLikeDeleteStatus.PHOTO_NOT_FOUND;

        if (photo.getAlbumId() != album.getId())
            return PhotoLikeDeleteStatus.PHOTO_NOT_IN_ALBUM;

        if (!photo.getUsers().contains(user))
            return PhotoLikeDeleteStatus.PHOTO_NOT_LIKED;

        photoLikeRepository.deletePhotoLike(user, photo);
        return PhotoLikeDeleteStatus.PHOTO_LIKE_DELETED;
    }

    public int countPhotoLikes(Photo photo) {
        if (photo == null || photo.getId() <= 0)
            return 0;

        return photoLikeRepository.countPhotoLikes(photo);
    }
}