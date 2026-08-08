package pl.edu.agh.mwo.hibernate.filealbummanager.service;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Photo;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.PhotoLikeRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.PhotoLikeStatus;

public class PhotoLikeService {

    private final PhotoLikeRepository photoLikeRepository;

    public PhotoLikeService(PhotoLikeRepository photoLikeRepository) {
        this.photoLikeRepository = photoLikeRepository;
    }

    public PhotoLikeStatus checkPhotoLikeStatus(User user, String albumName, String photoName) {
        return photoLikeRepository.checkPhotoLikeStatus(user, albumName, photoName);
    }

    public boolean addPhotoLike(Photo photo, User user) {
        return photoLikeRepository.addPhotoLike(photo, user);
    }

    public boolean deletePhotoLike(Photo photo, User user) {
        return photoLikeRepository.deletePhotoLike(photo, user);
    }

    public int countedPhotoLikes(Photo photo) {
        return photoLikeRepository.countPhotoLikes(photo);
    }
}