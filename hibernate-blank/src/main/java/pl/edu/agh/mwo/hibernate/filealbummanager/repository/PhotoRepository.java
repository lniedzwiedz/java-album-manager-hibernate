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

public class PhotoRepository {

    private final SessionFactory sessionFactory;

    public PhotoRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Photo getPhoto(String photoName, int albumId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Photo> query = session.createQuery("FROM Photo p " + "WHERE p.name = :name " + "AND p.albumId = :albumId", Photo.class);
            query.setParameter("name", photoName);
            query.setParameter("albumId", albumId);
            return query.uniqueResult();
        }
    }

    public List<Photo> getPhotos(int albumId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Photo> query = session.createQuery("FROM Photo p WHERE p.albumId = :albumId", Photo.class);
            query.setParameter("albumId", albumId);
            return query.list();
        }
    }

    public void save(Photo photo) {
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            try {
                session.save(photo);
                transaction.commit();

            } catch (Exception e) {
                if (transaction.isActive())
                    transaction.rollback();
                throw e;
            }
        }
    }

    public void delete(Photo photo) {
        if (photo == null)
            return;

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            try {
                Photo managedPhoto = session.get(Photo.class, photo.getId());

                if (managedPhoto != null) {
                    List<User> users = new ArrayList<>(managedPhoto.getUsers());

                    for (User user : users) {
                        User managedUser = session.get(User.class, user.getId());
                        if (managedUser != null)
                            managedUser.removePhoto(managedPhoto);
                    }
                    session.delete(managedPhoto);
                }
                transaction.commit();

            } catch (Exception e) {
                if (transaction.isActive())
                    transaction.rollback();
                throw e;
            }
        }
    }

    public List<Photo> getPhotos(User user, String albumName) {
        if (user == null)
            return new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Query<Album> albumQuery = session.createQuery("FROM Album a " + "WHERE a.name = :name " + "AND a.userId = :userId", Album.class);
            albumQuery.setParameter("name", albumName);
            albumQuery.setParameter("userId", user.getId());

            Album album = albumQuery.uniqueResult();
            if (album == null)
                return new ArrayList<>();

            Query<Photo> photoQuery = session.createQuery("FROM Photo p WHERE p.albumId = :albumId", Photo.class);
            photoQuery.setParameter("albumId", album.getId());
            return photoQuery.list();
        }
    }
}