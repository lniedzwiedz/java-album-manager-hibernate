package pl.edu.agh.mwo.hibernate.filealbummanager.service;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.AlbumRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.album.AlbumAddStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.album.AlbumDeleteStatus;

import java.util.List;

public class AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public Album getAlbum(String albumName) {
        return albumRepository.getAlbum(albumName);
    }

    public Album getAlbum(String albumName, int userId) {
        return albumRepository.getAlbum(albumName, userId);
    }

    public List<Album> getAlbums(int userId) {
        return albumRepository.getAlbums(userId);
    }

    public AlbumAddStatus addAlbum(User user, String albumName) {

        if (user == null || user.getId() <= 0)
            return AlbumAddStatus.LOGGED_USER_NOT_FOUND;

        if (albumName == null || albumName.isBlank())
            return AlbumAddStatus.ALBUM_DATA_NOT_FOUND;

        Album existingAlbum = albumRepository.getAlbum(albumName, user.getId());
        if (existingAlbum != null)
            return AlbumAddStatus.ALBUM_ALREADY_EXISTS;

        Album album = new Album();
        album.setName(albumName);
        album.setUserId(user.getId());

        albumRepository.save(album);
        return AlbumAddStatus.ALBUM_ADDED;
    }

    public AlbumDeleteStatus deleteAlbum(User user, String albumName) {
        if (user == null || user.getId() <= 0)
            return AlbumDeleteStatus.LOGGED_USER_NOT_FOUND;

        if (albumName == null || albumName.isBlank())
            return AlbumDeleteStatus.ALBUM_DATA_NOT_FOUND;

        Album album = albumRepository.getAlbum(albumName, user.getId());
        if (album == null || album.getId() <= 0)
            return AlbumDeleteStatus.ALBUM_NOT_FOUND;

        if (album.getUserId() != user.getId())
            return AlbumDeleteStatus.ALBUM_NOT_OWNED_BY_USER;

        albumRepository.delete(album);
        return AlbumDeleteStatus.ALBUM_DELETED;
    }

    public boolean albumExistsForUser(User userLogged, String albumName) {
        if (userLogged == null || userLogged.getId() <= 0)
            return false;

        if (albumName == null || albumName.isBlank())
            return false;

        return albumRepository.getAlbum(albumName, userLogged.getId()) != null;
    }
}