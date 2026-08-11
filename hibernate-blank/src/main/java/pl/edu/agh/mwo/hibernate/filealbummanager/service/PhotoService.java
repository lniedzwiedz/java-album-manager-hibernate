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

    public PhotoAddResult addPhoto(User user, Album album, String photoName) {
        if (user == null || user.getId() <= 0)
            return PhotoAddResult.LOGGED_USER_NOT_FOUND;

        if (album == null || album.getId() <= 0)
            return PhotoAddResult.ALBUM_NOT_FOUND;

        if (photoName == null || photoName.isBlank())
            return PhotoAddResult.PHOTO_DATA_NOT_FOUND;

        Photo existingPhoto = photoRepository.getPhoto(photoName, album.getId());
        if (existingPhoto != null)
            return PhotoAddResult.PHOTO_ALREADY_EXISTS;

        Photo photo = new Photo();
        photo.setName(photoName);
        photo.setAlbumId(album.getId());
        photo.setDate(LocalDate.now().toString());

        photoRepository.save(photo);
        return PhotoAddResult.PHOTO_ADDED;
    }

    public PhotoDeleteResult deletePhoto(User user, Album album, Photo photo) {
        if (user == null || user.getId() <= 0)
            return PhotoDeleteResult.LOGGED_USER_NOT_FOUND;

        if (album == null || album.getId() <= 0)
            return PhotoDeleteResult.ALBUM_NOT_FOUND;

        if (photo == null || photo.getId() <= 0)
            return PhotoDeleteResult.PHOTO_NOT_FOUND;

        photoRepository.delete(photo);
        return PhotoDeleteResult.PHOTO_DELETED;
    }
}