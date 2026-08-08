package pl.edu.agh.mwo.hibernate.filealbummanager.service;

import pl.edu.agh.mwo.hibernate.filealbummanager.result.AlbumAddResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.AlbumRepository;

import java.util.List;

public class AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public Album getAlbumFromDatabase(String albumName) {
        return albumRepository.getAlbumFromDatabase(albumName);
    }

    public Album getAlbumFromDatabase(String albumName, int userId) {
        return albumRepository.getAlbumFromDatabase(albumName, userId);
    }

    public List<Album> getAlbumsFromDatabase(int userId) {
        return albumRepository.getAlbumsFromDatabase(userId);
    }

    public void createNewAlbum(User user, String albumName) {
        if (user == null)
            return;
        albumRepository.createNewAlbum(user, albumName);
    }

    public AlbumAddResult checkAlbumAddStatus(User userLogged, String albumName) {
        if (userLogged == null || userLogged.getId() <= 0)
            return AlbumAddResult.INVALID_USER;

        Album album = albumRepository.getAlbumFromDatabase(albumName, userLogged.getId());
        if (album == null)
            return AlbumAddResult.CAN_BE_ADDED;

        return AlbumAddResult.ALREADY_EXISTS;
    }

    public boolean isAlbumBelongToUser(User userLogged, String albumName) {
        if (userLogged == null) return false;
        return albumRepository.getAlbumFromDatabase(albumName, userLogged.getId()) != null;
    }

    public void deleteAlbum(User userLogged, String albumName) {
        if (userLogged == null)
            return;
        albumRepository.deleteAlbum(userLogged, albumName);
    }
}