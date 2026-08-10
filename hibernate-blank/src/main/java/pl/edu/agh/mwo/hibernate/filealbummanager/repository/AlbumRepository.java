package pl.edu.agh.mwo.hibernate.filealbummanager.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;

import java.util.List;

public class AlbumRepository {

    private final SessionFactory sessionFactory;

    public AlbumRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Album getAlbum(String albumName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Album> query = session.createQuery("FROM Album a WHERE a.name = :name", Album.class);
            query.setParameter("name", albumName);
            return query.uniqueResult();
        }
    }

    public Album getAlbum(String albumName, int userId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Album> query = session.createQuery("FROM Album a " + "WHERE a.name = :name " + "AND a.userId = :userId", Album.class);
            query.setParameter("name", albumName);
            query.setParameter("userId", userId);
            return query.uniqueResult();
        }
    }

    public List<Album> getAlbums(int userId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Album> query = session.createQuery("FROM Album a WHERE a.userId = :userId", Album.class);
            query.setParameter("userId", userId);
            return query.list();
        }
    }

    public void save(Album album) {
        try (Session session = sessionFactory.openSession()) {
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
    }

    public void delete(Album album) {
        try (Session session = sessionFactory.openSession()) {
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