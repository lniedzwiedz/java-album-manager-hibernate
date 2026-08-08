package pl.edu.agh.mwo.hibernate.filealbummanager.service;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.AlbumRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.PhotoAddResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Photo;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.PhotoRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.PhotoLikeStatus;

import java.util.List;

public class PhotoService {

    private final AlbumRepository albumRepository;
    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository, AlbumRepository albumRepository) {
        this.photoRepository = photoRepository;
        this.albumRepository = albumRepository;
    }

    public Photo getPhotoFromDatabase(String photoName, int albumId) {
        return photoRepository.getPhoto(photoName, albumId);
    }

    public List<Photo> getPhotosFromDatabase(int albumId) {
        return photoRepository.getPhotos(albumId);
    }

    public List<Photo> getPhotosForUserAlbum(User user, String albumName) {
        return photoRepository.getPhotosForUserAlbum(user, albumName);
    }

    public PhotoAddResult checkPhotoCanBeAdded(User user, String albumName, String photoName) {
        if (user == null || user.getId() <= 0)
            return PhotoAddResult.INVALID_USER_OR_ALBUM;

        Album album = albumRepository.getAlbum(albumName, user.getId());
        if (album == null)
            return PhotoAddResult.INVALID_USER_OR_ALBUM;

        Photo photo = photoRepository.getPhoto(photoName, album.getId());
        if (photo == null)
            return PhotoAddResult.CAN_BE_ADDED;

        return PhotoAddResult.ALREADY_EXISTS;
    }

    public void addPhoto(Photo photo) {
        photoRepository.save(photo);
    }

    public boolean deletePhoto(Photo photo) {
        return photoRepository.delete(photo);
    }

    public PhotoLikeStatus checkPhotoLikeStatus(User user, String albumName, String photoName) {
        return photoRepository.checkPhotoLikeStatus(user, albumName, photoName);
    }

    public boolean addPhotoLike(Photo photo, User user) {
        return photoRepository.addPhotoLike(photo, user);
    }

    public boolean deletePhotoLike(Photo photo, User user) {
        return photoRepository.deletePhotoLike(photo, user);
    }

    public int countedPhotoLikes(Photo photo) {
        return photoRepository.countPhotoLikes(photo);
    }
}