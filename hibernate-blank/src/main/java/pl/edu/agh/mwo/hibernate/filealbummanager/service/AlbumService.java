package pl.edu.agh.mwo.hibernate.filealbummanager.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.AlbumAddResult;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Photo;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.repository.AlbumRepository;

import java.util.List;

public class AlbumService {

    private final Session session;
    private final AlbumRepository albumRepository;

    public AlbumService(Session session, AlbumRepository albumRepository) {
        this.session = session;
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

//    public void printAlbums() {
//        albumRepository.printAlbums();
//    }
//
//    public void printMyAlbums(User user) {
//        if (user == null)
//            return;
//
//        albumRepository.printUserAlbums(user);
//    }
//
//    public void printUserAlbums(User user) {
//        if (user == null)
//            return;
//
//        albumRepository.printUserAlbums(user);
//    }

    public void createNewAlbum(User user, String albumName) {
        if (user == null)
            return;

        albumRepository.createNewAlbum(user, albumName);
    }

    public AlbumAddResult getProcessingStatusWhileAddingAlbum(User userLogged, String albumName) {
        if (userLogged == null || userLogged.getId() <= 0)
            return AlbumAddResult.INVALID_USER;

        Album album = albumRepository.getAlbumFromDatabase(albumName, userLogged.getId());
        if (album == null)
            return AlbumAddResult.CAN_BE_ADDED;

        return AlbumAddResult.ALREADY_EXISTS;
    }

    public boolean isAlbumBelongToUser(User userLogged, String albumName) {
        if (userLogged == null)
            return false;

        return albumRepository.getAlbumFromDatabase(albumName, userLogged.getId()) != null;
    }

    public void deleteAlbum(User userLogged, String albumName) {
        if (userLogged == null)
            return;

        Album album = albumRepository.getAlbumFromDatabase(albumName, userLogged.getId());
        if (album == null)
            return;

        Transaction transaction = session.beginTransaction();

        try {
            List<Photo> photos = getPhotosFromDatabase(album.getId());
            for (Photo photo : photos) {
                deleteRelationBetweenPhotoAndUser(photo);
                album.removePhoto(photo);
                session.delete(photo);
            }

            session.delete(album);
            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive())
                transaction.rollback();

            throw e;
        }
    }

    private List<Photo> getPhotosFromDatabase(int albumId) {
        return session.createQuery("FROM Photo p " +
                "WHERE p.albumId = :albumId", Photo.class).setParameter("albumId", albumId).list();
    }

    private void deleteRelationBetweenPhotoAndUser(Photo photo) {
        if (photo == null)
            return;

        List<User> users = session.createQuery("FROM User", User.class).list();
        for (User user : users) {
            if (user.getPhotos().contains(photo)) {
                user.removePhoto(photo);
                session.save(user);
            }
        }

        photo.getUsers().clear();
        session.save(photo);
    }
}