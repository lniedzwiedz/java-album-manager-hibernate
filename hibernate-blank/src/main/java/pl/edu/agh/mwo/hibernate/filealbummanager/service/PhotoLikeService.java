package pl.edu.agh.mwo.hibernate.filealbummanager.service;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Photo;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.PhotoLikeRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.PhotoLikeStatus;

public class PhotoLikeService {

    private final PhotoLikeRepository photoLikeRepository;

    public PhotoLikeService(PhotoLikeRepository photoLikeRepository) {
        this.photoLikeRepository = photoLikeRepository;
    }

    public PhotoLikeStatus checkPhotoLikeStatus(User user, User ownerUser, Album album, Photo photo) {
        if (user == null || ownerUser == null || album == null || photo == null) {
            return PhotoLikeStatus.PHOTO_LIKE_ERROR;
        }

        boolean areFriends =
                user.getId() == ownerUser.getId()
                        || user.getUsers().contains(ownerUser)
                        || ownerUser.getUsers().contains(user);

        if (!areFriends) {
            return PhotoLikeStatus.NOT_FRIEND_PHOTO_OWNER;
        }

        if (photo.getAlbumId() != album.getId()) {
            return PhotoLikeStatus.PHOTO_NOT_IN_ALBUM;
        }

        if (photo.getUsers().contains(user)) {
            return PhotoLikeStatus.ALREADY_LIKED;
        }

        return PhotoLikeStatus.NEVER_LIKED;
    }

    public boolean addPhotoLike(Photo photo, User user) {
        return photoLikeRepository.addPhotoLike(photo, user);
    }

    public boolean deletePhotoLike(User user, Photo photo) {
        return photoLikeRepository.deletePhotoLike(user, photo);
    }

    public int countPhotoLikes(Photo photo) {
        return photoLikeRepository.countPhotoLikes(photo);
    }
}