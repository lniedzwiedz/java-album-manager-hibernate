package pl.edu.agh.mwo.hibernate.filealbummanager.service;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Photo;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.AlbumRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.PhotoRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.photo.PhotoAddStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.photo.PhotoDeleteStatus;

import java.time.LocalDate;
import java.util.List;

public class PhotoService {

    private final AlbumRepository albumRepository;
    private final PhotoRepository photoRepository;

    public PhotoService(AlbumRepository albumRepository, PhotoRepository photoRepository) {
        this.albumRepository = albumRepository;
        this.photoRepository = photoRepository;
    }

    public Photo getPhoto(String photoName, int albumId) {
        return photoRepository.getPhoto(photoName, albumId);
    }

    public List<Photo> getPhotos(int albumId) {
        return photoRepository.getPhotos(albumId);
    }

    public List<Photo> getPhotos(User user, String albumName) {
        return photoRepository.getPhotos(user, albumName);
    }

    public PhotoAddStatus addPhoto(User userLogged, String albumName, String photoName) {
        if (userLogged == null || userLogged.getId() <= 0)
            return PhotoAddStatus.LOGGED_USER_NOT_FOUND;

        if (albumName == null || albumName.isBlank())
            return PhotoAddStatus.ALBUM_DATA_NOT_FOUND;

        Album album = albumRepository.getAlbum(albumName, userLogged.getId());
        if (album == null || album.getId() <= 0)
            return PhotoAddStatus.ALBUM_NOT_FOUND;

        if (album.getUserId() != userLogged.getId())
            return PhotoAddStatus.ALBUM_NOT_OWNED_BY_USER;

        if (photoName == null || photoName.isBlank())
            return PhotoAddStatus.PHOTO_DATA_NOT_FOUND;

        Photo existingPhoto = photoRepository.getPhoto(photoName, album.getId());
        if (existingPhoto != null)
            return PhotoAddStatus.PHOTO_ALREADY_EXISTS;

        Photo photo = new Photo();
        photo.setName(photoName);
        photo.setAlbumId(album.getId());
        photo.setDate(LocalDate.now().toString());

        photoRepository.save(photo);
        return PhotoAddStatus.PHOTO_ADDED;
    }

    public PhotoDeleteStatus deletePhoto(User userLogged, String albumName, String photoName) {
        if (userLogged == null || userLogged.getId() <= 0)
            return PhotoDeleteStatus.LOGGED_USER_NOT_FOUND;

        if (albumName == null || albumName.isBlank())
            return PhotoDeleteStatus.ALBUM_DATA_NOT_FOUND;

        Album album = albumRepository.getAlbum(albumName, userLogged.getId());
        if (album == null || album.getId() <= 0)
            return PhotoDeleteStatus.ALBUM_NOT_FOUND;

        if (album.getUserId() != userLogged.getId())
            return PhotoDeleteStatus.ALBUM_NOT_OWNED_BY_USER;

        if (photoName == null || photoName.isBlank())
            return PhotoDeleteStatus.PHOTO_DATA_NOT_FOUND;

        Photo photo = photoRepository.getPhoto(photoName, album.getId());
        if (photo == null || photo.getId() <= 0)
            return PhotoDeleteStatus.PHOTO_NOT_FOUND;

        if (photo.getAlbumId() != album.getId())
            return PhotoDeleteStatus.PHOTO_NOT_IN_ALBUM;

        photoRepository.delete(photo);
        return PhotoDeleteStatus.PHOTO_DELETED;
    }
}