package pl.edu.agh.mwo.hibernate.filealbummanager.service;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.album.AlbumAddResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.AlbumRepository;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.album.AlbumDeleteResult;

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

    public AlbumAddResult addAlbum(User user, String albumName) {
        if (user == null)
            return AlbumAddResult.LOGGED_USER_NOT_FOUND;

        if (albumName == null || albumName.isBlank())
            return AlbumAddResult.ALBUM_ADD_FORBIDDEN;

        if (albumRepository.getAlbum(albumName, user.getId()) != null)
            return AlbumAddResult.ALBUM_ALREADY_EXISTS;

        Album album = new Album();
        album.setName(albumName);
        album.setUserId(user.getId());

        albumRepository.save(album);
        return AlbumAddResult.ALBUM_ADDED;
    }

    public AlbumDeleteResult deleteAlbum(User user, String albumName) {
        if (user == null)
            return AlbumDeleteResult.LOGGED_USER_NOT_FOUND;

        if (albumName == null || albumName.isBlank())
            return AlbumDeleteResult.ALBUM_DELETE_FORBIDDEN;

        Album album = albumRepository.getAlbum(albumName, user.getId());
        if (album == null)
            return AlbumDeleteResult.ALBUM_NOT_FOUND;

        albumRepository.delete(album);
        return AlbumDeleteResult.ALBUM_DELETED;
    }

    public boolean albumExistsForUser(User userLogged, String albumName) {
        if (userLogged == null)
            return true;
        return albumRepository.getAlbum(albumName, userLogged.getId()) == null;
    }
}