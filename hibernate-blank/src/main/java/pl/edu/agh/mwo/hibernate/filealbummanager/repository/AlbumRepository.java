package pl.edu.agh.mwo.hibernate.filealbummanager.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.Messages;

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

    public boolean isAlbumBelongToUser(User user, String albumName) {
        if (user == null)
            return false;
        return getAlbumFromDatabase(albumName, user.getId()) != null;
    }

    public int getProcessingStatusWhileAddingAlbum(User user, String albumName) {
        if (user == null || user.getId() <= 0) {
            System.out.println(String.format(Messages.ERROR_USER_NOT_EXIST_E1, user != null ? user.getName() : ""));
            return 3;
        }
        Album album = getAlbumFromDatabase(albumName, user.getId());
        if (album == null)
            return 1;
        return 2;
    }

    public void createNewAlbum(User user, String albumName) {
        if (user == null)
            return;
        Album album = new Album();
        album.setName(albumName);
        album.setUserId(user.getId());
        Transaction transaction = session.beginTransaction();
        try {
            session.save(album);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive())
                transaction.rollback();
            throw e;
        }
    }

    public void deleteAlbum(User user, String albumName) {
        if (user == null)
            return;
        Album album = getAlbumFromDatabase(albumName, user.getId());
        if (album == null) {
            System.out.println(String.format(Messages.ALBUM_NOT_EXIST, albumName));
            return;
        }
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(album);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive())
                transaction.rollback();
            throw e;
        }
    }

    public void printAlbums() {
        Query<Album> query = session.createQuery("FROM Album", Album.class);
        List<Album> albums = query.list();
        System.out.println(Messages.ALBUMS_HEADER);
        for (Album album : albums) {
            System.out.println(album);
        }
    }

    public void printUserAlbums(User user) {
        if (user == null)
            return;
        System.out.println(String.format(Messages.ALBUMS_OWNER_HEADER, user.getName()));
        List<Album> albums = getAlbumsFromDatabase(user.getId());
        for (Album album : albums) {
            System.out.println(album);
        }
    }
}