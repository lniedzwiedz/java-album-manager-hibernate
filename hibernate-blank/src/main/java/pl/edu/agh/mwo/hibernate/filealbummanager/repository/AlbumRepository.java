package pl.edu.agh.mwo.hibernate.filealbummanager.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.ui.message.album.AlbumMessages;

import java.util.List;

public class AlbumRepository {

    private final SessionFactory sessionFactory;

    public AlbumRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Album getAlbumFromDatabase(String albumName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Album> query = session.createQuery("FROM Album a WHERE a.name = :name", Album.class);
            query.setParameter("name", albumName);
            return query.uniqueResult();
        }
    }

    public Album getAlbumFromDatabase(String albumName, int userId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Album> query = session.createQuery("FROM Album a " + "WHERE a.name = :name " + "AND a.userId = :userId", Album.class);
            query.setParameter("name", albumName);
            query.setParameter("userId", userId);
            return query.uniqueResult();
        }
    }

    public List<Album> getAlbumsFromDatabase(int userId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Album> query = session.createQuery("FROM Album a WHERE a.userId = :userId", Album.class);
            query.setParameter("userId", userId);
            return query.list();
        }
    }

    public void createNewAlbum(User user, String albumName) {
        if (user == null)
            return;

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            try {
                Album album = new Album();
                album.setName(albumName);
                album.setUserId(user.getId());

                session.save(album);
                transaction.commit();

            } catch (Exception e) {
                if (transaction.isActive())
                    transaction.rollback();
                throw e;
            }
        }
    }

    public void deleteAlbum(User user, String albumName) {
        if (user == null)
            return;

        try (Session session = sessionFactory.openSession()) {

            Query<Album> query = session.createQuery("FROM Album a " + "WHERE a.name = :name " + "AND a.userId = :userId", Album.class);
            query.setParameter("name", albumName);
            query.setParameter("userId", user.getId());

            Album album = query.uniqueResult();
            if (album == null) {
                System.out.println(String.format(AlbumMessages.ALBUM_NOT_EXIST, albumName));
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
    }
}