package pl.edu.agh.mwo.hibernate.filealbummanager.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Album;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.Photo;
import pl.edu.agh.mwo.hibernate.filealbummanager.entity.User;
import pl.edu.agh.mwo.hibernate.filealbummanager.result.PhotoLikeStatus;

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

    public Album getAlbum(String albumName, int userId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Album> query = session.createQuery(
                    "FROM Album a " +
                            "WHERE a.name = :name " +
                            "AND a.userId = :userId",
                    Album.class
            );

            query.setParameter("name", albumName);
            query.setParameter("userId", userId);

            return query.uniqueResult();
        }
    }

    public void save(Photo photo) {
        if (photo == null)
            return;

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

    public boolean delete(Photo photo) {
        if (photo == null)
            return false;

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                Photo managedPhoto = session.get(Photo.class, photo.getId());
                if (managedPhoto == null) {
                    transaction.rollback();
                    return false;
                }
                deleteRelationBetweenPhotoAndUser(session, managedPhoto);
                session.delete(managedPhoto);
                transaction.commit();
                return true;

            } catch (Exception e) {
                if (transaction.isActive())
                    transaction.rollback();
                throw e;
            }
        }
    }

    public List<Photo> getPhotosForUserAlbum(User user, String albumName) {
        if (user == null) return new ArrayList<>();

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

    public PhotoLikeStatus checkPhotoLikeStatus(User user, String albumName, String photoName) {
        if (user == null) return PhotoLikeStatus.ALBUM_DOES_NOT_EXIST;

        try (Session session = sessionFactory.openSession()) {
            Query<Album> albumQuery = session.createQuery("FROM Album a WHERE a.name = :name", Album.class);
            albumQuery.setParameter("name", albumName);

            Album album = albumQuery.uniqueResult();
            if (album == null)
                return PhotoLikeStatus.ALBUM_DOES_NOT_EXIST;

            User owner = session.get(User.class, album.getUserId());
            User managedUser = session.get(User.class, user.getId());
            if (owner == null || managedUser == null)
                return PhotoLikeStatus.ALBUM_DOES_NOT_EXIST;

            boolean areFriends = managedUser.equals(owner) || managedUser.getUsers().contains(owner) || owner.getUsers().contains(managedUser);

            if (!areFriends)
                return PhotoLikeStatus.NOT_FRIEND_PHOTO_OWNER;

            Query<Photo> photoQuery = session.createQuery("FROM Photo p " + "WHERE p.name = :name " + "AND p.albumId = :albumId", Photo.class);

            photoQuery.setParameter("name", photoName);
            photoQuery.setParameter("albumId", album.getId());

            Photo photo = photoQuery.uniqueResult();
            if (photo == null)
                return PhotoLikeStatus.PHOTO_NOT_IN_ALBUM;

            if (photo.getUsers().contains(managedUser))
                return PhotoLikeStatus.ALREADY_LIKED;

            return PhotoLikeStatus.NEVER_LIKED;
        }
    }

    public boolean addPhotoLike(Photo photo, User user) {
        if (photo == null || user == null)
            return false;

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            try {
                Photo managedPhoto = session.get(Photo.class, photo.getId());
                User managedUser = session.get(User.class, user.getId());

                if (managedPhoto == null || managedUser == null) {
                    transaction.rollback();
                    return false;
                }

                if (managedPhoto.getUsers().contains(managedUser)) {
                    transaction.rollback();
                    return false;
                }
                managedPhoto.addUser(managedUser);
                transaction.commit();
                return true;

            } catch (Exception e) {
                if (transaction.isActive())
                    transaction.rollback();
                throw e;
            }
        }
    }

    public boolean deletePhotoLike(Photo photo, User user) {
        if (photo == null || user == null)
            return false;

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            try {
                Photo managedPhoto = session.get(Photo.class, photo.getId());
                User managedUser = session.get(User.class, user.getId());

                if (managedPhoto == null || managedUser == null) {
                    transaction.rollback();
                    return false;
                }

                if (!managedPhoto.getUsers().contains(managedUser)) {
                    transaction.rollback();
                    return false;
                }
                managedPhoto.removeUser(managedUser);
                managedUser.removePhoto(managedPhoto);
                transaction.commit();
                return true;

            } catch (Exception e) {
                if (transaction.isActive())
                    transaction.rollback();
                throw e;
            }
        }
    }

    public int countPhotoLikes(Photo photo) {
        if (photo == null)
            return 0;
        return photo.getUsers().size();
    }

    private void deleteRelationBetweenPhotoAndUser(Session session, Photo photo) {
        if (photo == null)
            return;

        List<User> users = new ArrayList<>(photo.getUsers());
        for (User user : users) {
            photo.removeUser(user);
            user.removePhoto(photo);
        }
    }
}