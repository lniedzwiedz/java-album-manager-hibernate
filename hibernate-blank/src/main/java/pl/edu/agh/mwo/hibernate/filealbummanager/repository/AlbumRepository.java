package pl.edu.agh.mwo.hibernate.filealbummanager.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.album.AlbumMessages;

import java.util.List;

public class AlbumRepository {

    private final Session session;

    public AlbumRepository(Session session) {
        this.session = session;
    }

    public Album getAlbumFromDatabase(String albumName) {
        Query<Album> query = session.createQuery("FROM Album a WHERE a.name = :name", Album.class);
        query.setParameter("name", albumName);
        return query.uniqueResult();
    }

    public Album getAlbumFromDatabase(String albumName, int userId) {
        Query<Album> query = session.createQuery("FROM Album a " + "WHERE a.name = :name " + "AND a.userId = :userId", Album.class);
        query.setParameter("name", albumName);
        query.setParameter("userId", userId);
        return query.uniqueResult();
    }

    public List<Album> getAlbumsFromDatabase(int userId) {
        Query<Album> query = session.createQuery("FROM Album a WHERE a.userId = :userId", Album.class);
        query.setParameter("userId", userId);
        return query.list();
    }

    public void createNewAlbum(User user, String albumName) {
        if (user == null) return;

        Album album = new Album();
        album.setName(albumName);
        album.setUserId(user.getId());

        Transaction transaction = session.beginTransaction();
        try {
            session.save(album);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();

            throw e;
        }
    }

    public void deleteAlbum(User user, String albumName) {
        if (user == null) return;

        Album album = getAlbumFromDatabase(albumName, user.getId());
        if (album == null) {
            System.out.println(String.format(AlbumMessages.ALBUM_NOT_EXIST, albumName));
            return;
        }

        Transaction transaction = session.beginTransaction();
        try {
            session.delete(album);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

//    public void printAlbums() {
//        Query<Album> query = session.createQuery("FROM Album", Album.class);
//        List<Album> albums = query.list();
//
//        System.out.println(AlbumMessages.ALBUMS_HEADER);
//
//        for (Album album : albums) {
//            System.out.println(album);
//        }
//    }
//
//    public void printUserAlbums(User user) {
//        if (user == null)
//            return;
//
//        System.out.println(String.format(AlbumMessages.ALBUMS_OWNER_HEADER, user.getName()));
//        List<Album> albums = getAlbumsFromDatabase(user.getId());
//
//        for (Album album : albums) {
//            System.out.println(album);
//        }
//    }
}