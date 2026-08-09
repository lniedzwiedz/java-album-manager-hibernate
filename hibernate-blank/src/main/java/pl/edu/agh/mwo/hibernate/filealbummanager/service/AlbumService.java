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

    public void createAlbum(User user, String albumName) {
        if (user == null)
            return;

        Album album = new Album();
        album.setName(albumName);
        album.setUserId(user.getId());
        albumRepository.save(album);
    }

    public AlbumAddResult checkAlbumAddStatus(User userLogged, String albumName) {
        if (userLogged == null || userLogged.getId() <= 0)
            return AlbumAddResult.INVALID_USER;

        Album album = albumRepository.getAlbum(albumName, userLogged.getId());
        if (album == null)
            return AlbumAddResult.CAN_BE_ADDED;

        return AlbumAddResult.ALREADY_EXISTS;
    }

    public AlbumDeleteResult checkAlbumDeleteStatus(User user, String albumName) {
        if (user == null || user.getId() <= 0)
            return AlbumDeleteResult.INVALID_USER;

        Album album = getAlbum(albumName, user.getId());
        if (album == null)
            return AlbumDeleteResult.ALBUM_NOT_FOUND;

        return AlbumDeleteResult.CAN_BE_DELETED;
    }

    public boolean albumExistsForUser(User userLogged, String albumName) {
        if (userLogged == null)
            return true;
        return albumRepository.getAlbum(albumName, userLogged.getId()) == null;
    }

    public boolean deleteAlbum(User userLogged, String albumName) {
        if (userLogged == null)
            return false;
        return albumRepository.delete(userLogged, albumName);
    }
}