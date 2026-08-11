package pl.edu.agh.mwo.hibernate.filealbummanager.service;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Photo;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.PhotoLikeRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.photolike.PhotoLikeAddResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.photolike.PhotoLikeDeleteResult;

public class PhotoLikeService {

    private final PhotoLikeRepository photoLikeRepository;

    public PhotoLikeService(PhotoLikeRepository photoLikeRepository) {
        this.photoLikeRepository = photoLikeRepository;
    }

    public PhotoLikeAddResult addPhotoLike(User user, User friend, Album album, Photo photo) {
        if (user == null || user.getId() <= 0)
            return PhotoLikeAddResult.LOGGED_USER_NOT_FOUND;

        if (friend == null || friend.getId() <= 0)
            return PhotoLikeAddResult.PHOTO_OWNER_NOT_FOUND;

        boolean areFriends = user.getId() == friend.getId() ||
                        user.getUsers().contains(friend) ||
                        friend.getUsers().contains(user);

        if (!areFriends)
            return PhotoLikeAddResult.NOT_FRIEND_PHOTO_OWNER;

        if (album == null || album.getId() <= 0)
            return PhotoLikeAddResult.ALBUM_NOT_FOUND;

        if (photo == null || photo.getId() <= 0)
            return PhotoLikeAddResult.PHOTO_NOT_FOUND;

        if (photo.getAlbumId() != album.getId())
            return PhotoLikeAddResult.PHOTO_NOT_IN_ALBUM;

        if (photo.getUsers().contains(user))
            return PhotoLikeAddResult.ALREADY_LIKED;

        photoLikeRepository.addPhotoLike(user, photo);
        return PhotoLikeAddResult.PHOTO_LIKE_ADDED;
    }

    public PhotoLikeDeleteResult deletePhotoLike(User user, User ownerUser, Album album, Photo photo) {
        if (user == null || user.getId() <= 0)
            return PhotoLikeDeleteResult.LOGGED_USER_NOT_FOUND;

        if (ownerUser == null || ownerUser.getId() <= 0)
            return PhotoLikeDeleteResult.PHOTO_OWNER_NOT_FOUND;

        boolean areFriends = user.getId() == ownerUser.getId() ||
                            user.getUsers().contains(ownerUser) ||
                            ownerUser.getUsers().contains(user);

        if (!areFriends)
            return PhotoLikeDeleteResult.NOT_FRIEND_PHOTO_OWNER;

        if (album == null || album.getId() <= 0)
            return PhotoLikeDeleteResult.ALBUM_NOT_FOUND;

        if (photo == null || photo.getId() <= 0)
            return PhotoLikeDeleteResult.PHOTO_NOT_FOUND;

        if (photo.getAlbumId() != album.getId())
            return PhotoLikeDeleteResult.PHOTO_NOT_IN_ALBUM;

        if (!photo.getUsers().contains(user))
            return PhotoLikeDeleteResult.NOT_LIKED;

        photoLikeRepository.deletePhotoLike(user, photo);
        return PhotoLikeDeleteResult.PHOTO_LIKE_DELETED;
    }

    public int countPhotoLikes(Photo photo) {
        if (photo == null || photo.getId() <= 0)
            return 0;

        return photoLikeRepository.countPhotoLikes(photo);
    }
}