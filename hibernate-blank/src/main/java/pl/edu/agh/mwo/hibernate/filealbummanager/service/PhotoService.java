package pl.edu.agh.mwo.hibernate.filealbummanager.service;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Photo;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.AlbumRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.PhotoRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.photo.PhotoAddResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.photo.PhotoDeleteResult;

import java.time.LocalDate;
import java.util.List;

public class PhotoService {

    private final PhotoRepository photoRepository;
    private final AlbumRepository albumRepository;

    public PhotoService(PhotoRepository photoRepository, AlbumRepository albumRepository) {

        this.photoRepository = photoRepository;
        this.albumRepository = albumRepository;
    }

    public Photo getPhoto(String photoName, int albumId) {

        return photoRepository.getPhoto(photoName, albumId);
    }

    public List<Photo> getPhoto(int albumId) {
        return photoRepository.getPhotos(albumId);
    }

    public List<Photo> getPhotos(User user, String albumName) {

        return photoRepository.getPhotos(user, albumName);
    }

    public PhotoAddResult addPhoto(User user, String albumName, String photoName) {
        if (user == null || user.getId() <= 0)
            return PhotoAddResult.INVALID_USER_OR_ALBUM;

        Album album = albumRepository.getAlbum(albumName, user.getId());
        if (album == null)
            return PhotoAddResult.INVALID_USER_OR_ALBUM;

        Photo existingPhoto = photoRepository.getPhoto(photoName, album.getId());
        if (existingPhoto != null)
            return PhotoAddResult.ALREADY_EXISTS;

        Photo photo = new Photo();
        photo.setName(photoName);
        photo.setAlbumId(album.getId());
        photo.setDate(LocalDate.now().toString());

        photoRepository.save(photo);
        return PhotoAddResult.PHOTO_ADDED;
    }

    public PhotoDeleteResult checkPhotoDeleteStatus(User user, String albumName, String photoName) {
        if (user == null || user.getId() <= 0)
            return PhotoDeleteResult.DELETE_FORBIDDEN;

        Album album = albumRepository.getAlbum(albumName, user.getId());
        if (album == null)
            return PhotoDeleteResult.DELETE_FORBIDDEN;

        Photo photo = photoRepository.getPhoto(photoName, album.getId());
        if (photo == null)
            return PhotoDeleteResult.PHOTO_NOT_FOUND;

        return PhotoDeleteResult.PHOTO_DELETED;
    }

    public void deletePhoto(User user, String albumName, String photoName) {
        Album album = albumRepository.getAlbum(albumName, user.getId());
        if (album == null)
            return;

        Photo photo = photoRepository.getPhoto(photoName, album.getId());
        if (photo == null)
            return;

        photoRepository.delete(photo);
    }
}