package pl.edu.agh.mwo.hibernate.filealbummanager.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Photo;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;

import java.util.ArrayList;
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
        if (album == null)
            return;

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            try {
                Album managedAlbum = session.get(Album.class, album.getId());
                if (managedAlbum == null) {
                    transaction.rollback();
                    return;
                }

                List<Photo> photos = new ArrayList<>(managedAlbum.getPhotos());
                for (Photo photo : photos) {

                    List<User> users = new ArrayList<>(photo.getUsers());
                    for (User user : users) {
                        User managedUser = session.get(User.class, user.getId());
                        if (managedUser != null)
                            managedUser.removePhoto(photo);
                    }
                }
                session.delete(managedAlbum);
                transaction.commit();

            } catch (Exception e) {
                if (transaction.isActive())
                    transaction.rollback();
                throw e;
            }
        }
    }
}