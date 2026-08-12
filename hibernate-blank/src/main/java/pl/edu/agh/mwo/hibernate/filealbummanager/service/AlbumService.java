package pl.edu.agh.mwo.hibernate.filealbummanager.service;

import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.AlbumRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.status.album.AddAlbumStatus;
import pl.edu.agh.mwo.hibernate.filealbummanager.status.album.DeleteAlbumStatus;

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

    public AddAlbumStatus addAlbum(User user, String albumName) {

        if (user == null || user.getId() <= 0)
            return AddAlbumStatus.LOGGED_USER_NOT_FOUND;

        if (albumName == null || albumName.isBlank())
            return AddAlbumStatus.ALBUM_DATA_NOT_FOUND;

        Album existingAlbum = albumRepository.getAlbum(albumName, user.getId());
        if (existingAlbum != null)
            return AddAlbumStatus.ALBUM_ALREADY_EXISTS;

        Album album = new Album();
        album.setName(albumName);
        album.setUserId(user.getId());

        albumRepository.save(album);
        return AddAlbumStatus.ALBUM_ADDED;
    }

    public DeleteAlbumStatus deleteAlbum(User user, String albumName) {
        if (user == null || user.getId() <= 0)
            return DeleteAlbumStatus.LOGGED_USER_NOT_FOUND;

        if (albumName == null || albumName.isBlank())
            return DeleteAlbumStatus.ALBUM_DATA_NOT_FOUND;

        Album album = albumRepository.getAlbum(albumName, user.getId());
        if (album == null || album.getId() <= 0)
            return DeleteAlbumStatus.ALBUM_NOT_FOUND;

        if (album.getUserId() != user.getId())
            return DeleteAlbumStatus.ALBUM_NOT_OWNED_BY_USER;

        albumRepository.delete(album);
        return DeleteAlbumStatus.ALBUM_DELETED;
    }

    public boolean albumExistsForUser(User userLogged, String albumName) {
        if (userLogged == null || userLogged.getId() <= 0)
            return false;

        if (albumName == null || albumName.isBlank())
            return false;

        return albumRepository.getAlbum(albumName, userLogged.getId()) != null;
    }
}