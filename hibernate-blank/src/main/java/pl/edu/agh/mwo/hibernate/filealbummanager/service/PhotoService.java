package pl.edu.agh.mwo.hibernate.filealbummanager.service;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.PhotoAddResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Photo;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.PhotoRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.PhotoLikeStatus;

import java.util.List;

public class PhotoService {

    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public Photo getPhotoFromDatabase(String photoName, int albumId) {
        return photoRepository.getPhotoFromDatabase(photoName, albumId);
    }

    public List<Photo> getPhotosFromDatabase(int albumId) {
        return photoRepository.getPhotosFromDatabase(albumId);
    }

    public List<Photo> getPhotosForUserAlbum(User user, String albumName) {
        return photoRepository.getPhotosForUserAlbum(user, albumName);
    }

    public boolean isPhotoBelongToUser(User user, String albumName, String photoName) {
        return photoRepository.isPhotoBelongToUser(user, albumName, photoName);
    }

    public PhotoAddResult getProcessingStatusWhileAddingPhoto(User user, String albumName, String photoName) {
        return photoRepository.getProcessingStatusWhileAddingPhoto(user, albumName, photoName);
    }

    public void addPhoto(String photoName, String albumName, User user) {
        photoRepository.addPhoto(photoName, albumName, user);
    }

    public void deletePhoto(String photoName, String albumName, User user) {
        photoRepository.deletePhoto(photoName, albumName, user);
    }

    public PhotoLikeStatus getProcessingStatusForPhotoLike(User user, String albumName, String photoName) {
        return photoRepository.getProcessingStatusForPhotoLike(user, albumName, photoName);
    }

    public void addPhotoLike(User user, String albumName, String photoName) {
        photoRepository.addPhotoLike(user, albumName, photoName);
    }

    public void deletePhotoLike(User user, String albumName, String photoName) {
        photoRepository.deletePhotoLike(user, albumName, photoName);
    }

    public int countedPhotoLikes(Photo photo) {
        return photoRepository.countedPhotoLikes(photo);
    }
}