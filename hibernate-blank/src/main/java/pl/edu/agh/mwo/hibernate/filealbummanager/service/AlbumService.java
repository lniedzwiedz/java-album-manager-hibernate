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

    public Album getAlbum(String albumName) {
        return albumRepository.getAlbum(albumName);
    }

    public Album getAlbum(String albumName, int userId) {
        return albumRepository.getAlbum(albumName, userId);
    }

    public List<Album> getAlbums(int userId) {
        return albumRepository.getAlbums(userId);
    }

//    public void createNewAlbum(User user, String albumName) {
//        if (user == null)
//            return;
//        albumRepository.createNewAlbum(user, albumName);
//    }

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

    public boolean doesAlbumBelongToUser(User userLogged, String albumName) {
        if (userLogged == null)
            return false;
        return albumRepository.getAlbum(albumName, userLogged.getId()) != null;
    }

    public boolean deleteAlbum(User userLogged, String albumName) {
        if (userLogged == null)
            return false;
        return albumRepository.delete(userLogged, albumName);
    }
}